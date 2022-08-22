package com.waka.dana.na.presentation.screen

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.waka.dana.na.R
import com.waka.dana.na.databinding.FragmentMainBinding
import com.waka.dana.na.domain.model.StorageItem
import com.waka.dana.na.domain.response.DataResult
import com.waka.dana.na.presentation.base.MasterController
import com.waka.dana.na.presentation.base.MasterEpoxyBuilder
import com.waka.dana.na.presentation.screen.detail.DetailDialogFragment
import com.waka.dana.na.presentation.screen.holder.ChildEpoxyModel_
import com.waka.dana.na.presentation.screen.holder.ChildLoadingEpoxyModel_
import com.waka.dana.na.presentation.screen.holder.NavigationEpoxyModel_
import com.waka.dana.na.presentation.screen.model.DisplayMode
import com.waka.dana.na.presentation.screen.model.Sort
import com.waka.dana.na.presentation.screen.search.all.SearchDialogFragment
import com.waka.dana.na.presentation.screen.search.filter.FilterDialogFragment
import com.waka.dana.na.util.HumanUtil
import com.waka.dana.na.util.visibleIf
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

/**
 * Created by hvngoc on 7/29/22
 */
class MainFragment : Fragment(), KoinComponent, MasterEpoxyBuilder,
    PopupMenu.OnMenuItemClickListener {
    companion object {

        const val TAG = "FragmentMain"

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    private val requestLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { allow ->
            if (allow) {
                prepareData()
            }
        }

    private lateinit var binding: FragmentMainBinding
    private val mainController by lazy { MasterController(this) }
    private val mainViewModel: MainViewModel by viewModel()

    private val navigationController by lazy {
        MasterController(object : MasterEpoxyBuilder {
            override fun buildHolder(): List<EpoxyModelWithHolder<*>> {
                val headers = mainViewModel.header.value ?: return emptyList()
                return headers.map { header ->
                    NavigationEpoxyModel_()
                        .id(header.path)
                        .title(header.name)
                        .onClick {
                            mainViewModel.popHeader(header)
                        }
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recyclerView.setController(mainController)

        binding.navigation.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.navigation.setController(navigationController)

        binding.headerSort.setOnClickListener {
            val menu = PopupMenu(requireActivity(), it)
            menu.menuInflater.inflate(R.menu.menu_sort, menu.menu)
            menu.setOnMenuItemClickListener(this)
            menu.show()
        }

        binding.displayMode.setOnClickListener {
            mainViewModel.toggleDisplayMode()
        }
        binding.filter.setOnClickListener {
            val filter = FilterDialogFragment.newInstance(
                mainViewModel.header.value?.lastOrNull()?.name,
                mainViewModel.header.value?.lastOrNull()?.path,
            )
            filter.show(childFragmentManager, FilterDialogFragment.TAG)
        }
        binding.search.setOnClickListener {
            val search = SearchDialogFragment.newInstance()
            search.show(childFragmentManager, SearchDialogFragment.TAG)
        }

        showContent(content = true)
        checkStoragePermission()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.data.observe(viewLifecycleOwner) {
            when (it) {
                is DataResult.Success<*> -> {
                    showContent(content = true)
                    mainController.requestModelBuild()
                    showLoadingContent(loading = false)
                }
                is DataResult.Error -> {
                    showContent(error = true)
                    binding.error.text = it.e.message ?: getString(R.string.error_default)
                }
                is DataResult.Loading -> {
                    showContent(content = true)
                    mainController.requestModelBuild()
                    showLoadingContent(loading = true)
                }
            }
        }
        mainViewModel.header.observe(viewLifecycleOwner) {
            navigationController.requestModelBuild()
        }
        mainViewModel.displayMode.observe(viewLifecycleOwner) { mode ->
            if (mode == DisplayMode.LIST) {
                binding.displayMode.setImageResource(R.drawable.vector_ic_mode_grid)
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            } else {
                binding.displayMode.setImageResource(R.drawable.vector_ic_mode_list)
                binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.date -> {
                mainViewModel.saveSortOption(Sort.DATE)
            }
            R.id.size -> {
                mainViewModel.saveSortOption(Sort.SIZE)
            }
            R.id.name -> {
                mainViewModel.saveSortOption(Sort.NAME)
            }
            R.id.type -> {
                mainViewModel.saveSortOption(Sort.TYPE)
            }
        }
        return true
    }

    private fun checkStoragePermission() {
        val self = ActivityCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (self != PackageManager.PERMISSION_GRANTED) {
            requestLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        } else {
            prepareData()
        }
    }

    private fun prepareData() {
        (mainViewModel.data.value as? DataResult.Success<*>)?.let {
            showContent(content = true)
            mainController.requestModelBuild()
        } ?: run {
            mainViewModel.loadData()
        }
    }

    private fun showContent(
        content: Boolean = false,
        error: Boolean = false,
        empty: Boolean = false,
    ) {
        binding.container.visibleIf(content)
        binding.error.visibleIf(error)
        binding.noResult.visibleIf(empty)
    }

    private fun showLoadingContent(loading: Boolean = true) {
        binding.headerLoading.visibleIf(loading)
        binding.headerContent.visibleIf(!loading)
    }

    override fun buildHolder(): List<EpoxyModelWithHolder<*>> {
        val raw = mainViewModel.data.value
        val data = raw as? DataResult.Success<*> ?: return buildLoadingHolder()
        val list = data.data as? List<StorageItem> ?: return emptyList()
        buildSortByHeader(list.size)
        return list.mapIndexed { index, file ->
            return@mapIndexed ChildEpoxyModel_()
                .id("$index-${file.path}")
                .title(file.name)
                .resThumbnail(if (file.isFolder == true) R.drawable.vector_ic_folder_open else R.drawable.vector_ic_file)
                .description(HumanUtil.displayDate(file.lastModified))
                .onClick {
                    if (file.isFolder == true) {
                        mainViewModel.loadFolder(file.name, file.path)
                    } else {
                        val detail = DetailDialogFragment.newInstance()
                        detail.show(childFragmentManager, DetailDialogFragment.TAG)
                    }
                }.onInfoClick {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Info")
                        .setMessage(file.toString())
                        .setPositiveButton("OK", null)
                        .show()
                }
        }
    }

    private fun buildLoadingHolder(): List<EpoxyModelWithHolder<*>> {
        val result = arrayListOf<EpoxyModelWithHolder<*>>()
        for (i in 0..10) {
            val holder = ChildLoadingEpoxyModel_().id(i)
            result.add(holder)
        }
        return result;
    }

    private fun buildSortByHeader(total: Int) {
        val text = resources.getQuantityString(R.plurals.total_items, total, total)
        binding.headerTotal.text = text
        binding.headerSort.text = getString(mainViewModel.getSort().resId)
    }
}
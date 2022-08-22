package com.waka.dana.na.presentation.screen.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.waka.dana.na.R
import com.waka.dana.na.databinding.FragmentFilterBinding
import com.waka.dana.na.domain.model.StorageItem
import com.waka.dana.na.domain.response.DataResult
import com.waka.dana.na.presentation.base.FullWindowDialogFragment
import com.waka.dana.na.presentation.base.MasterController
import com.waka.dana.na.presentation.base.MasterEpoxyBuilder
import com.waka.dana.na.presentation.screen.holder.ChildEpoxyModel_
import com.waka.dana.na.presentation.screen.holder.ChildLoadingEpoxyModel_
import com.waka.dana.na.util.HumanUtil
import com.waka.dana.na.util.visibleIf
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by hvngoc on 8/22/22
 */
class SearchDialogFragment : FullWindowDialogFragment(), MasterEpoxyBuilder {
    companion object {
        const val TAG = "DialogSearch"

        private const val DELAY_TIME = 600L

        fun newInstance(): SearchDialogFragment {
            return SearchDialogFragment()
        }
    }

    private val debounce = Runnable {
        val length = mainViewModel.lastQuery?.length ?: 0
        if (length >= 2) {
            showContent(content = true)
            mainViewModel.loadData(mainViewModel.lastQuery)
        } else {
            showContent(empty = true)
        }
    }
    private val mainViewModel: SearchViewModel by viewModel()

    private lateinit var binding: FragmentFilterBinding
    private val controller by lazy {
        MasterController(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter, container, false)
        binding.back.setOnClickListener {
            dismiss()
        }
        showContent(empty = true)
        binding.edit.hint = getString(R.string.search_in_all)

        binding.recyclerView.setController(controller)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.edit.doAfterTextChanged {
            mainViewModel.lastQuery = it?.toString()
            binding.edit.removeCallbacks(debounce)
            binding.edit.postDelayed(debounce, DELAY_TIME)
        }
        mainViewModel.data.observe(viewLifecycleOwner) {
            when (it) {
                is DataResult.Success<*> -> {
                    showContent(content = true)
                    controller.requestModelBuild()
                }
                is DataResult.Error -> {
                    showContent(empty = true)
                }
                is DataResult.Loading -> {
                    showContent(content = true)
                    controller.requestModelBuild()
                }
            }
        }
    }

    private fun showContent(content: Boolean = false, empty: Boolean = false) {
        binding.recyclerView.visibleIf(content)
        binding.empty.visibleIf(empty)
    }

    override fun buildHolder(): List<EpoxyModelWithHolder<*>> {
        val raw = mainViewModel.data.value
        val data = raw as? DataResult.Success<*> ?: return buildLoadingHolder()
        val list = data.data as? List<StorageItem> ?: return emptyList()
        if (list.isEmpty()) {
            showContent(empty = true)
            return emptyList()
        }
        return list.mapIndexed { index, file ->
            return@mapIndexed ChildEpoxyModel_()
                .id("$index-${file.path}")
                .title(file.name)
                .resThumbnail(if (file.isFolder == true) R.drawable.vector_ic_folder_open else R.drawable.vector_ic_file)
                .description(HumanUtil.displayDate(file.lastModified))
                .onClick {
                    Toast.makeText(
                        requireContext(),
                        "search current path only, click = TBD",
                        Toast.LENGTH_SHORT
                    ).show()
                }

        }
    }

    private fun buildLoadingHolder(): List<EpoxyModelWithHolder<*>> {
        val result = arrayListOf<EpoxyModelWithHolder<*>>()
        for (i in 0..10) {
            val holder = ChildLoadingEpoxyModel_().id(i)
            result.add(holder)
        }
        return result
    }

}
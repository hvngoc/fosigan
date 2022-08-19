package com.waka.dana.na.presentation.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.waka.dana.na.R
import com.waka.dana.na.databinding.FragmentMainBinding
import com.waka.dana.na.domain.model.WeatherList
import com.waka.dana.na.domain.response.DataResult
import com.waka.dana.na.presentation.base.MasterController
import com.waka.dana.na.presentation.base.MasterEpoxyBuilder
import com.waka.dana.na.presentation.screen.holder.ChildEpoxyModel_
import com.waka.dana.na.util.HumanUtil
import com.waka.dana.na.util.visibleIf
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

/**
 * Created by hvngoc on 7/29/22
 */
class MainFragment : Fragment(), KoinComponent, MasterEpoxyBuilder {
    companion object {
        private const val DELAY_TIME = 600L
        const val TAG = "FragmentMain"
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    private val controller = MasterController(this)
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: FragmentMainBinding

    private val debounce = Runnable {
        val length = mainViewModel.lastQuery?.length ?: 0
        if (length >= 3) {
            showContent(loading = true)
            mainViewModel.loadData(mainViewModel.lastQuery)
        } else {
            showContent(empty = true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        showContent(empty = true)
        binding.recyclerView.setController(controller)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayout.VERTICAL
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editText.doAfterTextChanged {
            mainViewModel.lastQuery = it?.toString()
            binding.editText.removeCallbacks(debounce)
            binding.editText.postDelayed(debounce, DELAY_TIME)
        }
        mainViewModel.data.observe(viewLifecycleOwner) {
            when (it) {
                is DataResult.Success<*> -> {
                    showContent(content = true)
                    controller.requestModelBuild()
                }
                is DataResult.Error -> {
                    showContent(error = true)
                    binding.error.text = it.e.message ?: getString(R.string.error_default)
                }
            }
        }
    }

    private fun showContent(
        content: Boolean = false,
        error: Boolean = false,
        empty: Boolean = false,
        loading: Boolean = false
    ) {
        binding.recyclerView.visibleIf(content)
        binding.error.visibleIf(error)
        binding.noResult.visibleIf(empty)
        binding.loading.visibleIf(loading)

    }

    override fun buildHolder(): List<EpoxyModelWithHolder<*>> {
        val data = mainViewModel.data.value as? DataResult.Success<*> ?: return emptyList()
        val list = data.data as? WeatherList ?: return emptyList()
        val items = list.list ?: return emptyList()
        return items.mapIndexed { index, weather ->
            val generateId = "${mainViewModel.lastQuery}$index${weather.dt}"
            return@mapIndexed ChildEpoxyModel_().id(generateId)
                .date(HumanUtil.displayDate(weather.dt))
                .temperature(weather.temp?.eve?.toString())
                .humidity("${weather.humidity}")
                .pressure(weather.pressure?.toString())
                .description(weather.weather?.getOrNull(0)?.description)
        }
    }
}
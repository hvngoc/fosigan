package com.waka.dana.na.presentation.screen.search.filter

import android.os.Bundle
import com.waka.dana.na.R
import com.waka.dana.na.domain.request.SearchInPath
import com.waka.dana.na.presentation.screen.search.BaseSearchDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by hvngoc on 8/22/22
 */
class FilterDialogFragment : BaseSearchDialogFragment<SearchInPath>() {
    companion object {
        const val TAG = "DialogFilter"
        private const val PARAM_NAME = "param_name"
        private const val PARAM_PATH = "param_path"

        fun newInstance(name: String?, path: String?): FilterDialogFragment {
            val fragment = FilterDialogFragment().apply {
                val bundle = Bundle().apply {
                    putString(PARAM_NAME, name)
                    putString(PARAM_PATH, path)
                }
                arguments = bundle
            }
            return fragment
        }
    }

    override val mainViewModel: FilterViewModel by viewModel()

    override fun getHint(): String {
        val name = arguments?.getString(PARAM_NAME)
        return getString(R.string.search_in_hint, name)
    }

    override fun getRequestData(): SearchInPath {
        val path = arguments?.getString(PARAM_PATH)
        return SearchInPath(mainViewModel.lastQuery, path)
    }
}
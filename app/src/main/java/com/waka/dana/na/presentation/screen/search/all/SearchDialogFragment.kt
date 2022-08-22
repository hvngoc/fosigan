package com.waka.dana.na.presentation.screen.search.all

import com.waka.dana.na.R
import com.waka.dana.na.presentation.screen.search.BaseSearchDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by hvngoc on 8/22/22
 */
class SearchDialogFragment : BaseSearchDialogFragment<String>() {
    companion object {
        const val TAG = "DialogSearch"

        fun newInstance(): SearchDialogFragment {
            return SearchDialogFragment()
        }
    }

    override val mainViewModel: SearchViewModel by viewModel()

    override fun getHint(): String {
        return getString(R.string.search_in_all)
    }

    override fun getRequestData(): String? {
        return mainViewModel.lastQuery
    }

    override val toastMessage: String = "search file in devices only, click = TBD"
}
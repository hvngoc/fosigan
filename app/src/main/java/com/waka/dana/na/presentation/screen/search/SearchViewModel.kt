package com.waka.dana.na.presentation.screen.search

import androidx.lifecycle.viewModelScope
import com.waka.dana.na.domain.response.DataResult
import com.waka.dana.na.domain.usecase.SearchStorageByName
import com.waka.dana.na.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by hvngoc on 8/22/22
 */
class SearchViewModel(private val useCase: SearchStorageByName) : BaseViewModel() {

    override val initState: DataResult = DataResult.Error(Exception())

    var lastQuery: String? = null

    fun loadData(query: String?) {
        _data.value = DataResult.Loading
        viewModelScope.launch(Dispatchers.Main + exceptionHandler) {
            useCase.execute(query, {
                _data.value = it
            }, {
                _data.value = it
            })
        }
    }
}
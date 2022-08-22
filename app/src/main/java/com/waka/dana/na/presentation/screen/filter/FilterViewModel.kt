package com.waka.dana.na.presentation.screen.filter

import androidx.lifecycle.viewModelScope
import com.waka.dana.na.domain.request.SearchInPath
import com.waka.dana.na.domain.response.DataResult
import com.waka.dana.na.domain.usecase.SearchStorageInPath
import com.waka.dana.na.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by hvngoc on 8/22/22
 */
class FilterViewModel(private val useCase: SearchStorageInPath) : BaseViewModel() {

    override val initState: DataResult = DataResult.Error(Exception())

    var lastQuery: String? = null

    fun loadData(query: String?, path: String?) {
        _data.value = DataResult.Loading
        viewModelScope.launch(Dispatchers.Main + exceptionHandler) {
            val request = SearchInPath(query = query, path = path)
            useCase.execute(request, {
                _data.value = it
            }, {
                _data.value = it
            })
        }
    }
}
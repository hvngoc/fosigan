package com.waka.dana.na.presentation.screen.search

import androidx.lifecycle.viewModelScope
import com.waka.dana.na.domain.model.StorageItem
import com.waka.dana.na.domain.response.DataResult
import com.waka.dana.na.domain.usecase.BaseUseCase
import com.waka.dana.na.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by hvngoc on 8/22/22
 */
abstract class BaseSearchViewModel<T> : BaseViewModel() {
    abstract val useCase: BaseUseCase<T, List<StorageItem>>

    override val initState: DataResult = DataResult.Error(Exception())

    var lastQuery: String? = null

    fun loadData(request: T?) {
        _data.value = DataResult.Loading
        viewModelScope.launch(Dispatchers.Main + exceptionHandler) {
            useCase.execute(request, {
                _data.value = it
            }, {
                _data.value = it
            })
        }
    }
}
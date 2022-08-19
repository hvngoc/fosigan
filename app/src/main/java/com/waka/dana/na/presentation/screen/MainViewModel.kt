package com.waka.dana.na.presentation.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waka.dana.na.domain.response.DataResult
import com.waka.dana.na.domain.usecase.GetListWeatherByNameUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

/**
 * Created by hvngoc on 7/29/22
 */
class MainViewModel(private val useCase: GetListWeatherByNameUseCase) : ViewModel(), KoinComponent {

    private val exceptionHandler = CoroutineExceptionHandler { _, error ->
        _data.value = DataResult.Error(error)
    }

    var lastQuery: String? = null

    private val _data = MutableLiveData<DataResult>()
    val data: LiveData<DataResult> = _data

    fun loadData(query: String?) {
        viewModelScope.launch(Dispatchers.Main + exceptionHandler) {
            useCase.execute(query, {
                _data.value = it
            }, {
                _data.value = it
            })
        }
    }
}
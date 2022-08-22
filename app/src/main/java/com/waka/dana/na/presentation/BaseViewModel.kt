package com.waka.dana.na.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waka.dana.na.domain.response.DataResult
import kotlinx.coroutines.CoroutineExceptionHandler
import org.koin.core.component.KoinComponent

/**
 * Created by hvngoc on 8/22/22
 */
abstract class BaseViewModel : ViewModel(), KoinComponent {

    protected val exceptionHandler = CoroutineExceptionHandler { _, error ->
        _data.value = DataResult.Error(error)
    }

    protected val _data: MutableLiveData<DataResult> by lazy {
        MutableLiveData<DataResult>().apply {
            value = DataResult.Loading
        }
    }
    val data: LiveData<DataResult> = _data
}
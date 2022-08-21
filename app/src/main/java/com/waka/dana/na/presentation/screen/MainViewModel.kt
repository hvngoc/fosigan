package com.waka.dana.na.presentation.screen

import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waka.dana.na.domain.model.StorageHeader
import com.waka.dana.na.domain.response.DataResult
import com.waka.dana.na.domain.usecase.GetListStorageByPath
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

/**
 * Created by hvngoc on 7/29/22
 */
class MainViewModel(private val useCase: GetListStorageByPath) : ViewModel(), KoinComponent {

    private val exceptionHandler = CoroutineExceptionHandler { _, error ->
        _data.value = DataResult.Error(error)
    }

    private val _data: MutableLiveData<DataResult> by lazy {
        MutableLiveData<DataResult>().apply {
            value = DataResult.Loading
        }
    }
    val data: LiveData<DataResult> = _data

    private val _header: MutableLiveData<List<StorageHeader>> by lazy {
        MutableLiveData<List<StorageHeader>>().apply {
            value = arrayListOf(
                StorageHeader(
                    name = "Internal Storage",
                    path = Environment.getExternalStorageDirectory().path
                )
            )
        }
    }
    val header: LiveData<List<StorageHeader>> = _header

    fun loadData() {
        _data.value = DataResult.Loading

        viewModelScope.launch(Dispatchers.Main + exceptionHandler) {
            val path = _header.value?.lastOrNull()?.path
            useCase.execute(params = path, {
                _data.value = it
            }, {
                _data.value = it
            })
        }
    }
}
package com.waka.dana.na.presentation.screen

import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waka.dana.na.data.caches.PrefServices
import com.waka.dana.na.domain.model.StorageHeader
import com.waka.dana.na.domain.model.StorageItem
import com.waka.dana.na.domain.response.DataResult
import com.waka.dana.na.domain.usecase.GetListStorageByPath
import com.waka.dana.na.presentation.screen.model.Sort
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

/**
 * Created by hvngoc on 7/29/22
 */
class MainViewModel(
    private val useCase: GetListStorageByPath,
    private val prefServices: PrefServices
) : ViewModel(), KoinComponent {

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
                _data.value = DataResult.Success(data = applySort(it.data))
            }, {
                _data.value = it
            })
        }
    }

    fun loadFolder(name: String?, path: String?) {
        val root = StorageHeader(name = name, path = path)
        val list = _header.value?.toMutableList()
        list?.add(root)
        _header.value = list
        loadData()
    }

    fun popHeader(header: StorageHeader) {
        val index = _header.value?.indexOf(header) ?: return
        if (index == -1 || index + 1 == _header.value?.size) {
            return
        }
        val list = _header.value?.subList(0, index + 1)
        _header.value = list
        loadData()
    }

    fun saveSortOption(sort: Sort) {
        prefServices.saveInt(Sort.KEY_SORT, sort.type)
        loadData()
    }

    fun getSort(): Sort {
        val type = prefServices.getInt(Sort.KEY_SORT, Sort.NAME.type)
        return Sort.fromType(type)
    }

    private fun applySort(list: List<StorageItem>?): List<StorageItem>? {
        return list
    }
}
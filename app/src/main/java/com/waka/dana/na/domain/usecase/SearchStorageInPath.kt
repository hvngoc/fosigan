package com.waka.dana.na.domain.usecase

import com.waka.dana.na.domain.model.StorageItem
import com.waka.dana.na.domain.repository.StorageRepository
import com.waka.dana.na.domain.request.SearchInPath

/**
 * Created by hvngoc on 7/29/22
 */
class SearchStorageInPath(private val repository: StorageRepository) :
    BaseUseCase<SearchInPath, List<StorageItem>>() {

    override suspend fun loadData(params: SearchInPath?): List<StorageItem>? {
        return repository.searchStorage(params?.path, params?.query)
    }
}
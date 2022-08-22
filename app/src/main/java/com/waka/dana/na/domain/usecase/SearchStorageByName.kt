package com.waka.dana.na.domain.usecase

import com.waka.dana.na.domain.model.StorageItem
import com.waka.dana.na.domain.repository.StorageRepository

/**
 * Created by hvngoc on 7/29/22
 */
class SearchStorageByName(private val repository: StorageRepository) :
    BaseUseCase<String, List<StorageItem>>() {

    override suspend fun loadData(params: String?): List<StorageItem>? {
        return repository.searchStorageByName(params)
    }
}
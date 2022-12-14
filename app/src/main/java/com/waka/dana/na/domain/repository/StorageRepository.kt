package com.waka.dana.na.domain.repository

import com.waka.dana.na.domain.model.StorageItem

/**
 * Created by hvngoc on 7/29/22
 */
interface StorageRepository {

    @Throws(Throwable::class)
    fun getListStorage(path: String?): List<StorageItem>?

    @Throws(Throwable::class)
    fun searchStorage(path: String?, query: String?): List<StorageItem>?

    @Throws(Throwable::class)
    fun searchStorageByName(name: String?): List<StorageItem>?
}
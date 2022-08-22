package com.waka.dana.na.data.caches

import com.waka.dana.na.domain.model.StorageItem

/**
 * Created by hvngoc on 8/22/22
 */
interface ProviderServices {

    @Throws(Throwable::class)
    fun searchStorageByName(name: String?): List<StorageItem>?
}
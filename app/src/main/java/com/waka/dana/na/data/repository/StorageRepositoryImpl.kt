package com.waka.dana.na.data.repository

import com.waka.dana.na.data.caches.ProviderServices
import com.waka.dana.na.domain.model.StorageItem
import com.waka.dana.na.domain.repository.StorageRepository
import java.io.File

/**
 * Created by hvngoc on 7/29/22
 */
class StorageRepositoryImpl(private val providerServices: ProviderServices) : StorageRepository {

    @Throws(Throwable::class)
    override fun getListStorage(path: String?): List<StorageItem>? {
        if (path == null) {
            return emptyList()
        }
        val file = File(path)
        val children = file.listFiles()
        if (children == null || children.isEmpty()) {
            return emptyList()
        }
        return children.map { item ->
            val totalSpace = try {
                item.length()
            } catch (e: Exception) {
                0L
            }
            StorageItem(
                name = item.name,
                path = item.absolutePath,
                lastModified = item.lastModified(),
                isFolder = item.isDirectory,
                size = totalSpace
            )
        }
    }

    @Throws(Throwable::class)
    override fun searchStorage(path: String?, query: String?): List<StorageItem>? {
        val list = getListStorage(path)
        return list?.filter { item ->
            item.name?.lowercase()?.contains(query?.lowercase() ?: "") ?: false
        }
    }

    @Throws(Throwable::class)
    override fun searchStorageByName(name: String?): List<StorageItem>? {
        return providerServices.searchStorageByName(name)
    }
}
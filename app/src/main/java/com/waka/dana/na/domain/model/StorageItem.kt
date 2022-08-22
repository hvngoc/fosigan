package com.waka.dana.na.domain.model


/**
 * Created by hvngoc on 8/21/22
 */
data class StorageItem(
    val name: String?,
    val path: String?,
    val lastModified: Long?,
    val size: Long?,
    val isFolder: Boolean?
)
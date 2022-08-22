package com.waka.dana.na.domain.model


/**
 * Created by hvngoc on 8/21/22
 */
data class StorageItem(
    val name: String? = null,
    val path: String?= null,
    val lastModified: Long? = null,
    val size: Long? = null,
    val isFolder: Boolean? = null
)
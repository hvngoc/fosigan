package com.waka.dana.na.domain.model


/**
 * Created by hvngoc on 8/21/22
 */
data class StorageItem(
    val name: String? = null,
    val path: String? = null,
    val lastModified: Long? = null,
    val size: Long? = null,
    val isFolder: Boolean? = null
) {
    override fun toString(): String {
        val title = if (isFolder == true) "Folder: " else "File: "
        return "$title $name - $path - $lastModified -$size"
    }
}
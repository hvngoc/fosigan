package com.waka.dana.na.data.caches

import android.content.Context
import android.provider.MediaStore
import android.provider.OpenableColumns
import com.waka.dana.na.domain.model.StorageItem

/**
 * Created by hvngoc on 8/22/22
 */
class ProviderServicesImpl(private val context: Context) : ProviderServices {

    override fun searchStorageByName(name: String?): List<StorageItem>? {
        val projection = arrayOf(
            OpenableColumns.DISPLAY_NAME,
            OpenableColumns.SIZE,
            MediaStore.MediaColumns.DATE_MODIFIED,
            MediaStore.MediaColumns.DATA
        )

        val c = context.contentResolver.query(
            MediaStore.Files.getContentUri("external"),
            projection,
            "${OpenableColumns.DISPLAY_NAME} like ?",
            arrayOf("%$name%"),
            null
        )
        val result = arrayListOf<StorageItem>()

        c?.let { cursor ->
            cursor.moveToFirst()
            do {
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                val dateIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DATE_MODIFIED)
                val dataIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DATA)

                val fileName = cursor.getString(nameIndex)
                val size = cursor.getLong(sizeIndex)
                val date = cursor.getLong(dateIndex)
                val path = cursor.getString(dataIndex)

                val item =
                    StorageItem(name = fileName, size = size, lastModified = date, path = path)
                result.add(item)
            } while (cursor.moveToNext())
            cursor.close()
        }
        return result
    }
}
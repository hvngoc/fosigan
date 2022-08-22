package com.waka.dana.na.data.caches

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.OpenableColumns
import com.waka.dana.na.domain.model.StorageItem
import java.io.File

/**
 * Created by hvngoc on 8/22/22
 */
class ProviderServicesImpl(private val context: Context) : ProviderServices {
    override fun searchStorageByName(name: String?): List<StorageItem>? {
        val uri = Uri.fromFile(File(Environment.getExternalStorageDirectory().path))
        val cursor = context.contentResolver.query(
            uri,
            null,
            "${OpenableColumns.DISPLAY_NAME} like ",
            arrayOf("%$name%"),
            null
        )
        cursor?.moveToFirst()

        val result = arrayListOf<StorageItem>()

        while (cursor?.moveToNext() == true) {
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)

            val fileName = cursor.getString(nameIndex)
            val size = cursor.getLong(sizeIndex)

            val item = StorageItem(name = fileName, size = size)
            result.add(item)
        }

        cursor?.close()

        return result
    }
}
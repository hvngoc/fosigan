package com.waka.dana.na.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by hvngoc on 7/29/22
 */
object HumanUtil {
    fun displayDate(dt: Long?): String? {
//        val data = dt?.toLongOrNull() ?: return null
        val data = dt ?: return null
        val format = SimpleDateFormat("EEE, MM/dd/yyyy", Locale.US)
        return format.format(Date(data * 1000))
    }
}
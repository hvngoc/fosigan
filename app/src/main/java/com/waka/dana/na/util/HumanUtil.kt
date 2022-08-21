package com.waka.dana.na.util

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by hvngoc on 7/29/22
 */
object HumanUtil {
    fun displayDate(dt: Long?): String? {
        val data = dt ?: return null
        val date = Date(data)
        if (DateUtils.isToday(data)) {
            val format = SimpleDateFormat("HH:mm", Locale.US)
            return format.format(date)
        }
        val formatAll = SimpleDateFormat("EEE, MM/dd/yyyy", Locale.US)
        return formatAll.format(date)
    }
}
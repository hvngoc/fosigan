package com.waka.dana.na.data.caches

/**
 * Created by hvngoc on 8/21/22
 */
interface PrefServices {

    fun saveInt(key: String, data: Int)

    fun getInt(key: String, default: Int): Int
}
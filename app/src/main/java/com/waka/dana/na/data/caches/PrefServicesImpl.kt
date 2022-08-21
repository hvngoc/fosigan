package com.waka.dana.na.data.caches

import android.content.Context

/**
 * Created by hvngoc on 8/21/22
 */
class PrefServicesImpl(private val context: Context) : PrefServices {

    companion object {
        private const val NAME = "wad-pref"
    }

    override fun saveInt(key: String, data: Int) {
        val shared = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        shared.edit().apply {
            putInt(key, data)
            apply()
        }
    }

    override fun getInt(key: String, default: Int): Int {
        val shared = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        return shared.getInt(key, default)
    }
}
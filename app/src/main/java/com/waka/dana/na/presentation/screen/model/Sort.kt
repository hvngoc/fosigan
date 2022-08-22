package com.waka.dana.na.presentation.screen.model

import com.waka.dana.na.R
import com.waka.dana.na.domain.model.SortBy
import com.waka.dana.na.domain.model.StorageItem

/**
 * Created by hvngoc on 8/21/22
 */
enum class Sort(val type: SortBy, val resId: Int, val rule: Comparator<StorageItem>) {
    SIZE(SortBy.SIZE, R.string.sort_by_size,
        Comparator<StorageItem> { p0, p1 ->
            val size0 = p0.size ?: 0.0
            val size1 = p1.size ?: 0.0
            return@Comparator if (size0 > size1) 1 else -1
        }),
    NAME(SortBy.NAME, R.string.sort_by_name,
        Comparator<StorageItem> { p0, p1 -> p0.name?.compareTo(p1.name ?: "") ?: -1 }),
    TYPE(SortBy.TYPE, R.string.sort_by_type,
        Comparator<StorageItem> { p0, p1 ->
            if (p0.isFolder == true && p1.isFolder == true) {
                return@Comparator 0
            }
            if (p0.isFolder == true && p1.isFolder == false) {
                return@Comparator -1
            }
            return@Comparator 1
        }),
    DATE(SortBy.DATE, R.string.sort_by_date,
        Comparator<StorageItem> { p0, p1 ->
            val last0 = p0.lastModified ?: 0L
            val last1 = p1.lastModified ?: 0L
            return@Comparator if (last0 > last1) 1 else -1
        });

    companion object {
        const val KEY_SORT = "KEY_SORT"

        fun fromType(type: Int): Sort {
            for (sort in values()) {
                if (sort.type.type == type) {
                    return sort
                }
            }
            return NAME
        }
    }
}
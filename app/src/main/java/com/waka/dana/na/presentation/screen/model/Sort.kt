package com.waka.dana.na.presentation.screen.model

import com.waka.dana.na.R
import com.waka.dana.na.domain.model.SortBy

/**
 * Created by hvngoc on 8/21/22
 */
enum class Sort(val type: Int, val resId: Int) {
    SIZE(SortBy.SIZE.type, R.string.sort_by_size),
    NAME(SortBy.NAME.type, R.string.sort_by_name),
    TYPE(SortBy.TYPE.type, R.string.sort_by_type),
    DATE(SortBy.DATE.type, R.string.sort_by_date);

    companion object {
        const val KEY_SORT = "KEY_SORT"

        fun fromType(type: Int): Sort {
            for (sort in values()) {
                if (sort.type == type) {
                    return sort
                }
            }
            return NAME
        }
    }
}
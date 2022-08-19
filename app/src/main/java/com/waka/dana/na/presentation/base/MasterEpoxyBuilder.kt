package com.waka.dana.na.presentation.base

import com.airbnb.epoxy.EpoxyModelWithHolder

/**
 * Created by hvngoc on 7/29/22
 */
interface MasterEpoxyBuilder {
    fun buildHolder(): List<EpoxyModelWithHolder<*>>
}
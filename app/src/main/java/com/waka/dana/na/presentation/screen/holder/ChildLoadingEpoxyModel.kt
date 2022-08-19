package com.waka.dana.na.presentation.screen.holder

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.waka.dana.na.R
import com.waka.dana.na.databinding.ItemChildLoadingBinding

/**
 * Created by hvngoc on 7/29/22
 */

@EpoxyModelClass(layout = R.layout.item_child_loading)
abstract class ChildLoadingEpoxyModel :
    EpoxyModelWithHolder<ChildLoadingEpoxyModel.ChildHolder>() {
    override fun bind(holder: ChildHolder) {
        //DOnt care
    }

    class ChildHolder : EpoxyHolder() {

        lateinit var binding: ItemChildLoadingBinding

        override fun bindView(itemView: View) {
            binding = ItemChildLoadingBinding.bind(itemView)
        }
    }
}
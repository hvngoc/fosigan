package com.waka.dana.na.presentation.screen.holder

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.waka.dana.na.R
import com.waka.dana.na.databinding.ItemNavigationBinding

/**
 * Created by hvngoc on 7/29/22
 */

@EpoxyModelClass(layout = R.layout.item_navigation)
abstract class NavigationEpoxyModel :
    EpoxyModelWithHolder<NavigationEpoxyModel.ChildHolder>() {

    @EpoxyAttribute
    var title: String? = null

    @EpoxyAttribute
    var onClick: (() -> Unit)? = null

    override fun bind(holder: ChildHolder) {
        holder.binding.button.text = title
        holder.binding.root.setOnClickListener {
            onClick?.invoke()
        }
    }

    class ChildHolder : EpoxyHolder() {

        lateinit var binding: ItemNavigationBinding

        override fun bindView(itemView: View) {
            binding = ItemNavigationBinding.bind(itemView)
        }
    }
}
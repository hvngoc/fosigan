package com.waka.dana.na.presentation.screen.holder

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.waka.dana.na.R
import com.waka.dana.na.databinding.ItemChildBinding

/**
 * Created by hvngoc on 7/29/22
 */

@EpoxyModelClass(layout = R.layout.item_child)
abstract class ChildEpoxyModel :
    EpoxyModelWithHolder<ChildEpoxyModel.ChildHolder>() {

    @EpoxyAttribute
    var resThumbnail: Int = R.drawable.vector_ic_folder_open

    @EpoxyAttribute
    var title: String? = null

    @EpoxyAttribute
    var description: String? = null

    @EpoxyAttribute
    var onClick: (() -> Unit)? = null

    @EpoxyAttribute
    var onInfoClick: (() -> Unit)? = null

    override fun bind(holder: ChildHolder) {
        holder.binding.thumbnail.setImageResource(resThumbnail)
        holder.binding.title.text = title
        holder.binding.description.text = description
        holder.binding.root.setOnClickListener {
            onClick?.invoke()
        }
        holder.binding.info.setOnClickListener {
            onInfoClick?.invoke()
        }
    }

    class ChildHolder : EpoxyHolder() {

        lateinit var binding: ItemChildBinding

        override fun bindView(itemView: View) {
            binding = ItemChildBinding.bind(itemView)
        }
    }
}
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
    var date: String? = null

    @EpoxyAttribute
    var temperature: String? = null

    @EpoxyAttribute
    var pressure: String? = null

    @EpoxyAttribute
    var humidity: String? = null

    @EpoxyAttribute
    var description: String? = null

    override fun bind(holder: ChildHolder) {
        val context = holder.binding.root.context
        holder.binding.date.text = context.getString(R.string.date, date)
        holder.binding.temperature.text = context.getString(R.string.temperature, temperature)
        holder.binding.pressure.text = context.getString(R.string.pressure, pressure)
        holder.binding.humidity.text = context.getString(R.string.humidity, humidity)
        holder.binding.description.text = context.getString(R.string.description, description)
    }

    class ChildHolder : EpoxyHolder() {

        lateinit var binding: ItemChildBinding

        override fun bindView(itemView: View) {
            binding = ItemChildBinding.bind(itemView)
        }
    }
}
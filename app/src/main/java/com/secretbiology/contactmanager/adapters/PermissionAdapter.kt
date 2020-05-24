package com.secretbiology.contactmanager.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.secretbiology.contactmanager.R
import com.secretbiology.contactmanager.commons.inflate

data class PermissionItems(val header: Int, val details: Int, val permission: Boolean = true)

class PermissionAdapter(private val items: MutableList<PermissionItems>) :
    RecyclerView.Adapter<PermissionAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PermissionAdapter.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.activity_landing_recycle_item))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(newItems: List<PermissionItems>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PermissionAdapter.ViewHolder, position: Int) {
        val item = items[position]
        val context: Context = holder.itemView.context
        holder.header.text = context.getString(item.header)
        holder.details.text = context.getString(item.details)
        var color = R.color.warn
        if (item.permission) {
            holder.icon.setImageResource(R.drawable.ic_checkmark_filled)
            color = R.color.success
        }
        ImageViewCompat.setImageTintList(
            holder.icon,
            ColorStateList.valueOf(ContextCompat.getColor(context, color))
        )

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon: ImageView = itemView.findViewById(R.id.la_item_img)
        var header: TextView = itemView.findViewById(R.id.la_item_header)
        var details: TextView = itemView.findViewById(R.id.la_item_details)
    }
}

package com.secretbiology.contactmanager.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.secretbiology.contactmanager.R
import com.secretbiology.contactmanager.commons.inflate
import kotlinx.android.synthetic.main.activity_landing_recycle_item.view.*

data class PermissionItems(val header: Int, val details: Int, val permission: Boolean = true)

class PermissionAdapter(private val items: List<PermissionItems>) :
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

    override fun onBindViewHolder(holder: PermissionAdapter.ViewHolder, position: Int) {
        val item = items[position]
        val context: Context = holder.itemView.context
        holder.header.text = context.getString(item.header)
        holder.details.text = context.getString(item.details)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon: ImageView = itemView.findViewById(R.id.la_item_img)
        var header: TextView = itemView.findViewById(R.id.la_item_header)
        var details: TextView = itemView.findViewById(R.id.la_item_details)
    }
}

package com.secretbiology.contactmanager.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.secretbiology.contactmanager.R
import com.secretbiology.contactmanager.commons.inflate
import com.secretbiology.contactmanager.models.Contact


class ContactListAdapter(
    private val contactList: List<Contact>,
    private val onClick: onContactClick
) :
    RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.fragment_contact_list_item))
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactList[position]
        contact.displayName?.let { holder.name.text = it }
        contact.letters?.let { holder.letter.text = it }
        holder.layout.setOnClickListener {
            onClick.contactClicked(contact)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon: ImageView = itemView.findViewById(R.id.cl_item_icon)
        var letter: TextView = itemView.findViewById(R.id.cl_item_letter)
        var name: TextView = itemView.findViewById(R.id.cl_item_name)
        var status: ImageView = itemView.findViewById(R.id.cl_item_status)
        var layout: ConstraintLayout = itemView.findViewById(R.id.cl_item_layout)
    }

    interface onContactClick {
        fun contactClicked(contact: Contact)
    }
}
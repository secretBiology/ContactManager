package com.secretbiology.contactmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.secretbiology.contactmanager.adapters.PermissionAdapter
import com.secretbiology.contactmanager.adapters.PermissionItems
import kotlinx.android.synthetic.main.activity_landing_card2.*

class Landing : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        val adapter = PermissionAdapter(getItems())
        la_recycler.adapter = adapter
        la_recycler.layoutManager = LinearLayoutManager(baseContext)
    }

    private fun getItems(): MutableList<PermissionItems> {
        return mutableListOf<PermissionItems>(
            PermissionItems(R.string.la_write_contacts, R.string.la_write_contacts_details),
            PermissionItems(R.string.la_phone_storage, R.string.la_phone_storage_details),
            PermissionItems(R.string.la_phone_call, R.string.la_phone_call_details)
        )
    }
}
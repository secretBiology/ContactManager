package com.secretbiology.contactmanager.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.secretbiology.contactmanager.R
import com.secretbiology.contactmanager.adapters.ContactListAdapter
import com.secretbiology.contactmanager.models.AppStateVM
import com.secretbiology.contactmanager.models.Contact
import com.secretbiology.contactmanager.models.ContactDetailsVM
import com.secretbiology.contactmanager.models.ContactListViewModel
import kotlinx.android.synthetic.main.fragment_contact_list.*

private const val TAG = "ContactList"

class ContactListFragment : Fragment(), ContactListAdapter.onContactClick {

    private lateinit var viewModel: ContactListViewModel
    private lateinit var contactDetailVM: ContactDetailsVM
    private lateinit var appModel: AppStateVM
    private lateinit var adapter: ContactListAdapter
    private val contactList = arrayListOf<Contact>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContactListViewModel::class.java)
        activity?.let {
            appModel = ViewModelProvider(it).get(AppStateVM::class.java)
            contactDetailVM = ViewModelProvider(it).get(ContactDetailsVM::class.java)
        }
        context?.let {
            viewModel.extractContacts(it)
            appModel.setProgress(true)
        }
        adapter = ContactListAdapter(contactList, this)
        cl_recycler.adapter = adapter
        cl_recycler.layoutManager = LinearLayoutManager(context)
        subscribe()
    }

    private fun subscribe() {
        viewModel.getContactList().observe(viewLifecycleOwner, Observer {
            appModel.setProgress(false)
            for (contact in it) {
                contactList.clear()
                contactList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
        contactDetailVM.getDetailedContact().observe(viewLifecycleOwner, Observer { con ->
            activity?.let {
                ContactDetailsFragment.newInstance(con.id)
                    .show(it.supportFragmentManager, "contact_details")
            } ?: kotlin.run {
                Log.e(TAG, "Activity could not be retrieved while showing contact details")
            }
        })
    }

    override fun contactClicked(contact: Contact) {
        context?.let {
            contactDetailVM.extractContactDetails(it, contact.id)
        } ?: kotlin.run {
            Log.e(TAG, "Context not found while showing contact details")
        }
    }

}
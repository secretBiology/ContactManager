package com.secretbiology.contactmanager.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.secretbiology.contactmanager.MainActivity
import com.secretbiology.contactmanager.MainApp
import com.secretbiology.contactmanager.R
import com.secretbiology.contactmanager.models.AppStateViewModel
import com.secretbiology.contactmanager.models.ContactListViewModel

private const val TAG = "ContactList"

class ContactListFragment : Fragment() {

    private lateinit var viewModel: ContactListViewModel
    private lateinit var appModel: AppStateViewModel

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
            appModel = ViewModelProvider(it).get(AppStateViewModel::class.java)
        }
        context?.let {
            viewModel.getContacts(it)
        }
    }

}
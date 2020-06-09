package com.secretbiology.contactmanager.fragments

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.secretbiology.contactmanager.R


const val ARG_CONTACT_ID = "contact_id"

class ContactDetailsFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            Log.i("TAG------", it.getInt(ARG_CONTACT_ID, 0).toString())
        }
    }

    companion object {
        fun newInstance(contactID: Int): ContactDetailsFragment =
            ContactDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_CONTACT_ID, contactID)
                }
            }

    }
}
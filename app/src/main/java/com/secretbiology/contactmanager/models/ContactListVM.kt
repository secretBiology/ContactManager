package com.secretbiology.contactmanager.models

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "ContactListVM"

class ContactListViewModel : ViewModel() {

    private val contactList = MutableLiveData<List<Contact>>()

    private val listProjection = arrayOf(
        Constants.ID,
        Constants.NAME,
        Constants.THUMB_URL,
        Constants.STARRED
    )

    fun getContactList(): LiveData<List<Contact>> {
        return contactList
    }


    fun extractContacts(context: Context) {

        val cursor = context.contentResolver.query(
            Constants.URL,
            listProjection,
            null,
            null,
            null
        )
        val contacts = mutableListOf<Contact>()
        cursor?.apply {
            while (moveToNext()) {
                val contact = Contact().apply {
                    id = getInt(getColumnIndex(Constants.ID))
                    displayName = getString(getColumnIndex(Constants.NAME))
                    photoThumb = getString(getColumnIndex(Constants.THUMB_URL))
                }
                contact.displayName?.let {
                    contact.letters = getLetters(it)
                    contacts.add(contact)
                } ?: kotlin.run {
                    Log.w(TAG, "Ignoring Contact ID: ${contact.id}")
                }
            }
        }
        cursor?.close()
        Log.i(TAG, "Total of ${contacts.size} contacts found")
        contactList.value = contacts
    }

    private fun getLetters(name: String): String {
        val k = name.split(" ").map { it.trim().first().toUpperCase() }
        return if (k.size == 1) {
            k[0].toString()
        } else {
            "${k.first()}${k.last()}"
        }
    }


}
package com.secretbiology.contactmanager.models

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.ViewModel
import com.secretbiology.contactmanager.commons.test

private const val TAG = "ContactListModel"

class ContactListViewModel : ViewModel() {


    private val projection = arrayOf(
        ContactsContract.Contacts._ID,
        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
        ContactsContract.Contacts.PHOTO_THUMBNAIL_URI,
        ContactsContract.Contacts.STARRED
    )
    

    fun getContacts(context: Context) {

        val cursor = context.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            projection,
            null,
            null,
            null
        )
        val contacts = mutableListOf<Contact>()
        cursor?.apply {
            while (moveToNext()) {
                val contact = Contact().apply {
                    id = getInt(getColumnIndex(ContactsContract.Contacts._ID))
                    displayName =
                        getString(getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))
                    photoThumb =
                        getString(getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI))
                    letters = getLetters(displayName!!)
                }
                contacts.add(contact)
            }
        }
        cursor?.close()
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
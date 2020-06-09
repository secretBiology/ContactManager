package com.secretbiology.contactmanager.models

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "ContactDetailsVM"


data class CursorData(val data: Map<Int, String?>)

class ContactDetailsVM : ViewModel() {

    private val currentContact = MutableLiveData<Contact>()

    fun getDetailedContact(): MutableLiveData<Contact> {
        return currentContact
    }

    fun extractContactDetails(context: Context, id: Int) {
        val selection = "${Constants.ID} = ?"
        val selectionArgs: Array<String> = arrayOf(id.toString())

        val cursor = context.contentResolver.query(
            Constants.URL,
            Constants.DATA_COLUMNS,
            selection,
            selectionArgs,
            null
        )
        val allDetails = mutableListOf<CursorData>()
        cursor?.apply {
            while (moveToNext()) {
                val dataMap = mutableMapOf<Int, String?>()
                val end = getColumnIndex(ContactsContract.Data.DATA15)
                for (i in 0..end) {
                    dataMap[i] = getString(i)
                }
                allDetails.add(CursorData(dataMap))
            }

        } ?: kotlin.run {
            Log.i(TAG, "Null Cursor while retrieving contact details")
        }
        cursor?.close()

        Log.i(TAG, allDetails.toString())
    }

    private fun getSections() {

    }
}
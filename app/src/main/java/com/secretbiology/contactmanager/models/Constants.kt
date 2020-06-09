package com.secretbiology.contactmanager.models

import android.net.Uri
import android.provider.ContactsContract


object Constants {

    val URL: Uri = ContactsContract.Data.CONTENT_URI

    const val TYPE: String = ContactsContract.Data.MIMETYPE
    const val TYPE_PHONE = ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
    const val TYPE_EVENT = ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE
    const val TYPE_EMAIL = ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE
    const val TYPE_GROUP = ContactsContract.CommonDataKinds.GroupMembership.CONTENT_ITEM_TYPE
    const val TYPE_IDENTITY = ContactsContract.CommonDataKinds.Identity.CONTENT_ITEM_TYPE
    const val TYPE_IM = ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE
    const val TYPE_NICKNAME = ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE
    const val TYPE_NOTE = ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE
    const val TYPE_ORGANIZATION = ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE
    const val TYPE_PHOTO = ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE
    const val TYPE_RELATION = ContactsContract.CommonDataKinds.Relation.CONTENT_ITEM_TYPE
    const val TYPE_SPI = ContactsContract.CommonDataKinds.SipAddress.CONTENT_ITEM_TYPE
    const val TYPE_NAME = ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE
    const val TYPE_ADDRESS = ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE
    const val TYPE_WEBSITE = ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE


    const val ID: String = ContactsContract.Data.CONTACT_ID
    const val NAME: String = ContactsContract.Data.DISPLAY_NAME
    const val THUMB_URL: String = ContactsContract.Data.PHOTO_THUMBNAIL_URI
    const val STARRED: String = ContactsContract.Data.STARRED

    val DATA_COLUMNS: Array<out String> = arrayOf(
        ContactsContract.Data._ID,
        ContactsContract.Data.MIMETYPE,
        ContactsContract.Data.DATA1,
        ContactsContract.Data.DATA2,
        ContactsContract.Data.DATA3,
        ContactsContract.Data.DATA4,
        ContactsContract.Data.DATA5,
        ContactsContract.Data.DATA6,
        ContactsContract.Data.DATA7,
        ContactsContract.Data.DATA8,
        ContactsContract.Data.DATA9,
        ContactsContract.Data.DATA10,
        ContactsContract.Data.DATA11,
        ContactsContract.Data.DATA12,
        ContactsContract.Data.DATA13,
        ContactsContract.Data.DATA14,
        ContactsContract.Data.DATA15
    )


}
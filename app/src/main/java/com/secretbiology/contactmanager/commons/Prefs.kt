package com.secretbiology.contactmanager.commons

import android.content.Context
import androidx.preference.PreferenceManager
import javax.inject.Inject
import javax.inject.Singleton


private const val PERMISSION_ASKED = "permission_asked"

@Singleton
class Prefs @Inject constructor(context: Context) {
    private val manager = PreferenceManager.getDefaultSharedPreferences(context)
    fun clearAll() {
        manager.edit().clear().apply()
    }

    fun arePermissionAsked(): Boolean {
        return manager.getBoolean(PERMISSION_ASKED, false)
    }

    fun dismissPermissions() {
        manager.edit().putBoolean(PERMISSION_ASKED, true).apply()
    }

}
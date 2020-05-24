package com.secretbiology.contactmanager

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.secretbiology.contactmanager.adapters.PermissionAdapter
import com.secretbiology.contactmanager.adapters.PermissionItems
import com.secretbiology.contactmanager.commons.Prefs
import com.secretbiology.contactmanager.commons.hideMe
import com.secretbiology.contactmanager.commons.showMe
import com.secretbiology.contactmanager.commons.toast
import kotlinx.android.synthetic.main.activity_landing.*
import kotlinx.android.synthetic.main.activity_landing_card1.*
import kotlinx.android.synthetic.main.activity_landing_card2.*
import javax.inject.Inject


class Landing : AppCompatActivity() {

    val PERMISSION_CODE = 1989 // Should NOT be private
    private lateinit var adapter: PermissionAdapter
    private val optionalPermissions = arrayOf(
        Manifest.permission.WRITE_CONTACTS,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CALL_PHONE
    )

    @Inject
    lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MainApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        adapter = PermissionAdapter(mutableListOf<PermissionItems>())
        la_recycler.adapter = adapter
        la_recycler.layoutManager = LinearLayoutManager(baseContext)

        la_mandatory_btn.setOnClickListener {
            askPermission(arrayOf(Manifest.permission.READ_CONTACTS))
        }
        la_optional_btn.setOnClickListener {
            askPermission(optionalPermissions)
        }
        la_dismiss.setOnClickListener {
            prefs.dismissPermissions()
            gotoHome()
        }
        la_continue.setOnClickListener {
            // If all permissions have given, do not show this activity again
            if (!la_mandatory_btn.isEnabled && !la_optional_btn.isEnabled) {
                prefs.dismissPermissions()
            }
            gotoHome()
        }
        adjustUI()
        if (prefs.arePermissionAsked()) {
            gotoHome()
        }
    }

    private fun gotoHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    adjustUI()
                } else {
                    baseContext.toast(getString(R.string.permission_denied))
                }
            }
        }
    }

    private fun adjustStatusBar(bar: View, btn: Button) {
        bar.setBackgroundColor(
            ContextCompat.getColor(
                baseContext,
                R.color.success
            )
        )
        btn.isEnabled = false
        btn.text = getString(R.string.granted)
    }

    private fun adjustUI() {
        val read = checkPermission(Manifest.permission.READ_CONTACTS)
        if (read) {
            adjustStatusBar(la_mandatory_bar, la_mandatory_btn)
        }
        if (isOptionalSatisfied()) {
            adjustStatusBar(la_optional_bar, la_optional_btn)
        }
        updateRecycler()

        la_continue.isEnabled = read
        if (read) {
            la_dismiss.showMe()
        } else {
            la_dismiss.hideMe()
        }
    }

    private fun askPermission(permission: Array<String>) {
        ActivityCompat.requestPermissions(this@Landing, permission, PERMISSION_CODE)
    }


    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            baseContext,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun isOptionalSatisfied(): Boolean {
        for (per in optionalPermissions) {
            if (!checkPermission(per)) {
                return false
            }
        }
        return true
    }

    private fun updateRecycler() {
        adapter.updateItems(getItems())
    }

    private fun getItems(): MutableList<PermissionItems> {
        return mutableListOf<PermissionItems>(
            PermissionItems(
                R.string.la_write_contacts,
                R.string.la_write_contacts_details,
                checkPermission(Manifest.permission.WRITE_CONTACTS)
            ),
            PermissionItems(
                R.string.la_phone_storage,
                R.string.la_phone_storage_details,
                checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ),
            PermissionItems(
                R.string.la_phone_call,
                R.string.la_phone_call_details,
                checkPermission(Manifest.permission.CALL_PHONE)
            )
        )
    }
}
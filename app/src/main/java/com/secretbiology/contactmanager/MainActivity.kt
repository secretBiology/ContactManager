package com.secretbiology.contactmanager

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.secretbiology.contactmanager.commons.hideMe
import com.secretbiology.contactmanager.commons.showMe
import com.secretbiology.contactmanager.models.AppStateVM
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appStateView: AppStateVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = main_fragment.findNavController()
        bottom_nav.setupWithNavController(navController)
        progress_bar.hideMe()
        appStateView = ViewModelProvider(this).get(AppStateVM::class.java)
        subscribe()
    }

    private fun subscribe() {
        appStateView.getProgress().observe(this, Observer {
            if (it) {
                progress_bar.showMe()
            } else {
                progress_bar.hideMe()
            }
        })
        appStateView.getError().observe(this, Observer {
            showError(it)
        })
    }

    fun showError(message: String) {
        AlertDialog.Builder(this@MainActivity)
            .setTitle(R.string.oops)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { _, _ -> }
            .show()
    }

}
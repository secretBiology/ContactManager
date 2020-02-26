package com.secretbiology.contactmanager

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Set up navigation
        navController = Navigation.findNavController(this, R.id.main_frame)
        bottom_nav.setupWithNavController(navController)

        //Rest of the code
        toggleProgressbar()
    }


    fun toggleProgressbar() {
        if (progress_frame.visibility == View.GONE) {
            progress_frame.visibility = View.VISIBLE
        } else {
            progress_frame.visibility = View.GONE
        }
    }
}
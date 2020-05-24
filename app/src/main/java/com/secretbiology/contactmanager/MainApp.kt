package com.secretbiology.contactmanager

import android.app.Application
import com.secretbiology.contactmanager.di.AppComponent
import com.secretbiology.contactmanager.di.DaggerAppComponent

open class MainApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}
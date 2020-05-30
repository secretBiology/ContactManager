package com.secretbiology.contactmanager.di

import android.content.Context
import com.secretbiology.contactmanager.Landing
import com.secretbiology.contactmanager.commons.Prefs
import com.secretbiology.contactmanager.fragments.ContactListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [StorageModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: Landing)
    fun inject(fragment: ContactListFragment)
    fun prefs(): Prefs
}
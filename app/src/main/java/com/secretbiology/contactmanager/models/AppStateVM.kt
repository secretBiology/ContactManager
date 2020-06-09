package com.secretbiology.contactmanager.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppStateVM : ViewModel() {
    private val showProgress = MutableLiveData<Boolean>()
    private val showError = MutableLiveData<String>()

    fun getProgress(): LiveData<Boolean> {
        return showProgress
    }

    fun setProgress(value: Boolean) {
        showProgress.value = value
    }

    fun getError(): LiveData<String> {
        return showError
    }

    fun setError(message: String) {
        setProgress(false)
        showError.value = message
    }
}
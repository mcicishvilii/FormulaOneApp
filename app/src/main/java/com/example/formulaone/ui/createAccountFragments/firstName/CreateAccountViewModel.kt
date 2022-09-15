package com.example.formulaone.ui.createAccountFragments.firstName

import androidx.lifecycle.ViewModel
import com.example.formulaone.DataStoreHandler

class CreateAccountViewModel : ViewModel() {

    suspend fun save(key: String, value: String) {
        DataStoreHandler.save(key, value)
    }

    fun getPreferences() = DataStoreHandler.getPreferences()

}
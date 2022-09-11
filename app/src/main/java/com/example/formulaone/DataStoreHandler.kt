package com.example.formulaone

import android.app.Application
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow

object DataStoreHandler {

    private val Application.store by preferencesDataStore(
        name = "test_name"
    )

    fun getPreferences(): Flow<Preferences> {
        return MyApp.appContext.store.data
    }

    suspend fun save(key: String, value: String) {
        MyApp.appContext.store.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    suspend fun clear() {
        MyApp.appContext.store.edit {
            it.clear()
        }
    }

    suspend fun remove(key: String) {
        MyApp.appContext.store.edit {
            it.remove(stringPreferencesKey(key))
        }
    }

}
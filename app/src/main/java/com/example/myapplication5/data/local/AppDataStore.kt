package com.example.myapplication5.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDataStore(private var context: Context) {

    private val Context.ds : DataStore<Preferences> by preferencesDataStore(name = "settings")

    companion object {
        val DARK_MODE = booleanPreferencesKey("dark_mode")
    }

    fun isDarkMode(): Flow<Boolean> {
        return context.ds.data.map {
            it[DARK_MODE] ?: false
        }
    }

    suspend fun setDarkMode(isDarkMode : Boolean) {
        context.ds.edit {
            it[DARK_MODE] = isDarkMode
        }
    }
}
package com.yunshen.yunapp.viewmodel

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//数据持久化
class StoreManager (private val context: Context){
    companion object{
        private val Context.dataStore by preferencesDataStore("settings")
        private val KEY_CHECKED = booleanPreferencesKey("checked")
        private val themeKey = booleanPreferencesKey("theme")
    }
    suspend fun updateChecked(checked: Boolean){
        context.dataStore.edit {
            it[KEY_CHECKED] = checked
        }
    }

    val checked:Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[KEY_CHECKED] == true
    }

    suspend fun updateTheme(theme: Boolean){
        context.dataStore.edit {
            it[themeKey] = theme
        }
    }

    val theme:Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[themeKey] == true
    }

}
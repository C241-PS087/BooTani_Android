package com.example.bootani.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveUserName(username: String) {
        dataStore.edit { preferences ->
            preferences[USERNAME] = username
        }
    }
    fun getLoginSession(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[LOGIN_SESSION] ?: false
        }
    }

    suspend fun saveLoginSession(loginSession: Boolean) {
        dataStore.edit { preferences ->
            preferences[LOGIN_SESSION] = loginSession
        }
    }

    fun getUserName(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[USERNAME] ?: ""
        }
    }

    fun getEmail(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[EMAIL] ?: ""
        }
    }

    suspend fun saveEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[EMAIL] = email
        }
    }

    suspend fun clearDataLogin() {
        dataStore.edit { preferences ->
            preferences.remove(USERNAME)
            preferences.remove(EMAIL)
            preferences.remove(LOGIN_SESSION)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }

        private val USERNAME = stringPreferencesKey("name")
        private val EMAIL = stringPreferencesKey("email")
        private val LOGIN_SESSION = booleanPreferencesKey("login_session")

    }
}
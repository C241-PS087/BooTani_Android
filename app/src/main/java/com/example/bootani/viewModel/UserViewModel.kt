package com.example.bootani.viewModel

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.bootani.data.UserPreferences
import kotlinx.coroutines.launch

class UserViewModel(private val pref: UserPreferences) : ViewModel() {

    fun getUserName(): LiveData<String> {
        return pref.getUserName().asLiveData()
    }fun getEmail(): LiveData<String> {
        return pref.getEmail().asLiveData()
    }

    fun saveUserName(username: String) {
        viewModelScope.launch {
            pref.saveUserName(username)
        }
    }fun saveEmail(email: String) {
        viewModelScope.launch {
            pref.saveEmail(email)
        }
    }

    fun clearDataLogin() {
        viewModelScope.launch {
            pref.clearDataLogin()
        }
    }

    fun getLoginSession(): LiveData<Boolean> {
        return pref.getLoginSession().asLiveData()
    }

    fun saveLoginSession(loginSession: Boolean) {
        viewModelScope.launch {
            pref.saveLoginSession(loginSession)
        }
    }



}
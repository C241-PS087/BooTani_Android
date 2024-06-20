package com.example.bootani.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bootani.api.APIConfig
import com.example.bootani.data.RequestLogin
import com.example.bootani.data.ResponseLogin
import com.example.bootani.data.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {


    private val _loginResponse = MutableLiveData<Response<ResponseLogin>>()
    val loginResponse: LiveData<Response<ResponseLogin>> get() = _loginResponse
    fun login(requestLogin: RequestLogin) {
        val api = APIConfig.getApiService().loginUser(requestLogin)
        api.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                _loginResponse.value = response
                Log.d("LoginViewModel", "onResponse: ${response.body()}")

            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                // Handle failure here, for example, by updating a LiveData with an error message
            }
        })
    }
}
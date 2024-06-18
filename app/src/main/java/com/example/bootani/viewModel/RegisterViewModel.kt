package com.example.bootani.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootani.api.APIConfig
import com.example.bootani.data.SignUpRequest
import com.example.bootani.data.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    private val _signUpResponse = MutableLiveData<Response<SignUpResponse>>()
    val signUpResponse: LiveData<Response<SignUpResponse>> = _signUpResponse

    fun signUp(requestSignUp: SignUpRequest) {
        APIConfig.getApiService().signUp(requestSignUp).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                _signUpResponse.value = response
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                // Handle failure here, for example, by updating a LiveData with an error message
            }
        })
    }
}
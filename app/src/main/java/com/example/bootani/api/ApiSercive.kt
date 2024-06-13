package com.example.bootani

import com.example.bootani.data.RequestLogin
import com.example.bootani.data.ResponseLogin
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/login")
    fun loginUser(@Body request: RequestLogin): Call<ResponseLogin>

    @POST("/signup")
    fun signUp(@Body request: SignUpRequest): Call<SignUpResponse>
}

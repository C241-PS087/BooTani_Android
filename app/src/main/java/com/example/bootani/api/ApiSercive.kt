package com.example.bootani.api

import com.example.bootani.data.SignUpRequest
import com.example.bootani.data.SignUpResponse
import com.example.bootani.data.RequestLogin
import com.example.bootani.data.ResponseLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("/login")
    fun loginUser(@Body request: RequestLogin): Call<ResponseLogin>

    @POST("/signup")
    fun signUp(@Body request: SignUpRequest): Call<SignUpResponse>

    @GET("artikel")
    suspend fun getArtikels(
        @Header("Authorization") token: String,
    ): ArtikelResponse
}

package com.example.bootani.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootani.api.APIConfig
import com.example.bootani.data.ResponseArtikelItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtikelViewModel : ViewModel() {
    private val artikels = MutableLiveData<List<ResponseArtikelItem>>()

    fun getArtikels(username: String) {
        APIConfig.getApiService().getArtikels(username).enqueue(object : Callback<List<ResponseArtikelItem>> {
            override fun onResponse(call: Call<List<ResponseArtikelItem>>, response: Response<List<ResponseArtikelItem>>) {
                if (response.isSuccessful) {
                    artikels.postValue(response.body())
                } else {
                    Log.d("ArtikelViewModel", "onResponse: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<ResponseArtikelItem>>, t: Throwable) {
                Log.d("ArtikelViewModel", "onFailure: ${t.message}")
            }
        })
    }

    fun getArtikelsLiveData(): MutableLiveData<List<ResponseArtikelItem>> {
        return artikels
    }
}
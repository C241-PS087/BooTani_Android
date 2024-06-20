package com.example.bootani.data

import com.google.gson.annotations.SerializedName

data class ResponseArtikel(

	@field:SerializedName("ResponseArtikel")
	val responseArtikel: List<ResponseArtikelItem>
)

data class ResponseArtikelItem(

	@field:SerializedName("image_url")
	val imageUrl: String,

	@field:SerializedName("tagline")
	val tagline: String,

	@field:SerializedName("content")
	val content: String
)

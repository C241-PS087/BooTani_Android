package com.example.bootani.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeItem(
    val foto: Int,
    val nama: String,
    val harga: String,
) : Parcelable
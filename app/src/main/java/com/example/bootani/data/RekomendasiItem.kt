package com.example.bootani.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RekomendasiItem(
    val foto: Int,
    val nama: String,
    val harga: String,
    val persentase: String
) : Parcelable
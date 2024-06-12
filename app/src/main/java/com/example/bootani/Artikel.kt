package com.example.bootani

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artikel(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
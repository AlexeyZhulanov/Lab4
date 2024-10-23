package com.example.lab4.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Long,
    var login: String,
    var password: String,
    var username: String
) : Parcelable
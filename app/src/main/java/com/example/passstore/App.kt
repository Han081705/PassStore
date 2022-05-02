package com.example.passstore

import android.os.Parcelable
import android.provider.ContactsContract
import kotlinx.parcelize.Parcelize

@Parcelize
data class App(
    var icon : String,
    var name : String,
    var appUser : String,
    var appPass : String,
    var appEmail: String
) : Parcelable

package com.example.formulaone.data.firebaseModels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MailAndPass(
    val mail:String = "",
    val password:String = ""
):Parcelable
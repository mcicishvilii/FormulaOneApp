package com.example.formulaone.ui.navMenuFragments.drivers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DriversDetails(
    val name:String,
    val lastName:String,
//    val photo:Int,
):Parcelable

package com.example.formulaone.ui.navMenuFragments.drivers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DriversDetails(
    val name:String,
    val lastName:String,
    val wins:String,
    val currentStanding:String,
    val nationality:String,
    val dob:String,
    val carNumber:String,
    val team:String,
    val qualis:String = ""
):Parcelable

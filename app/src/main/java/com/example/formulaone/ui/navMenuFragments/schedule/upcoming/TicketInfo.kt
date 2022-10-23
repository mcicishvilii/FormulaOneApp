package com.example.formulaone.ui.navMenuFragments.schedule.upcoming

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TicketInfo(
    val trackName:String,
    val date:String
):Parcelable

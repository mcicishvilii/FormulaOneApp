package com.example.formulaoneapplicationn.data.model.last_race


import com.google.gson.annotations.SerializedName

data class LastRaceDto(
    @SerializedName("MRData")
    val mRData: MRData = MRData()
)
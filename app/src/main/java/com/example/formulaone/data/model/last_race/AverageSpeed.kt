package com.example.formulaoneapplicationn.data.model.last_race


import com.google.gson.annotations.SerializedName

data class AverageSpeed(
    @SerializedName("speed")
    val speed: String = "",
    @SerializedName("units")
    val units: String = ""
)
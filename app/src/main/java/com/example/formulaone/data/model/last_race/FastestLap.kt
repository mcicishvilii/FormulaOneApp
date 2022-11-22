package com.example.formulaoneapplicationn.data.model.last_race


import com.google.gson.annotations.SerializedName

data class FastestLap(
    @SerializedName("AverageSpeed")
    val averageSpeed: AverageSpeed = AverageSpeed(),
    @SerializedName("lap")
    val lap: String = "",
    @SerializedName("rank")
    val rank: String = "",
    @SerializedName("Time")
    val time: Time = Time()
)
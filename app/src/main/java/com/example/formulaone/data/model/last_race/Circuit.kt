package com.example.formulaoneapplicationn.data.model.last_race


import com.google.gson.annotations.SerializedName

data class Circuit(
    @SerializedName("circuitId")
    val circuitId: String = "",
    @SerializedName("circuitName")
    val circuitName: String = "",
    @SerializedName("Location")
    val location: Location = Location(),
    @SerializedName("url")
    val url: String = ""
)
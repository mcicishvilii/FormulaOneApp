package com.example.formulaone.data.model.drivers.quali


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("country")
    val country: String,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("locality")
    val locality: String,
    @SerializedName("long")
    val long: String
)
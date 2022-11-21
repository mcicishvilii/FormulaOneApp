package com.example.formulaoneapplicationn.data.model.last_race


import com.google.gson.annotations.SerializedName

data class TimeX(
    @SerializedName("millis")
    val millis: String = "",
    @SerializedName("time")
    val time: String = ""
)
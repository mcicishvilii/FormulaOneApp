package com.example.formulaoneapplicationn.data.model.last_race


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("Constructor")
    val `constructor`: Constructor = Constructor(),
    @SerializedName("Driver")
    val driver: Driver = Driver(),
    @SerializedName("FastestLap")
    val fastestLap: FastestLap = FastestLap(),
    @SerializedName("grid")
    val grid: String = "",
    @SerializedName("laps")
    val laps: String = "",
    @SerializedName("number")
    val number: String = "",
    @SerializedName("points")
    val points: String = "",
    @SerializedName("position")
    val position: String = "",
    @SerializedName("positionText")
    val positionText: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("Time")
    val time: TimeX? = TimeX()
)
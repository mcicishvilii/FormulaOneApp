package com.example.formulaone.data.model.drivers.quali


import com.google.gson.annotations.SerializedName

data class RaceTable(
    @SerializedName("Races")
    val races: List<Race>,
    @SerializedName("season")
    val season: String
)
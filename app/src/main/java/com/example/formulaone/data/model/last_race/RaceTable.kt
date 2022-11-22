package com.example.formulaoneapplicationn.data.model.last_race


import com.google.gson.annotations.SerializedName

data class RaceTable(
    @SerializedName("Races")
    val races: List<Race> = listOf(),
    @SerializedName("round")
    val round: String = "",
    @SerializedName("season")
    val season: String = ""
)
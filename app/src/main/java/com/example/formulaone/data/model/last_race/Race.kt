package com.example.formulaoneapplicationn.data.model.last_race


import com.example.formulaoneapplicationn.domain.model.LastRaceInfoDomain
import com.google.gson.annotations.SerializedName

data class Race(
    @SerializedName("Circuit")
    val circuit: Circuit = Circuit(),
    @SerializedName("date")
    val date: String = "",
    @SerializedName("raceName")
    val raceName: String = "",
    @SerializedName("Results")
    val results: List<Result> = listOf(),
    @SerializedName("round")
    val round: String = "",
    @SerializedName("season")
    val season: String = "",
    @SerializedName("time")
    val time: String = "",
    @SerializedName("url")
    val url: String = ""
)

fun Race.toLastRaceInfoDomain(): LastRaceInfoDomain {
    return LastRaceInfoDomain(
        date, circuit, raceName, results, time
    )
}
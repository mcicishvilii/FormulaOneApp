package com.example.formulaone.data.model.drivers.quali


import com.example.formulaone.domain.model.QualiDomain
import com.google.gson.annotations.SerializedName

data class Race(
    @SerializedName("Circuit")
    val circuit: Circuit,
    @SerializedName("date")
    val date: String,
    @SerializedName("QualifyingResults")
    val qualifyingResults: List<QualifyingResult>,
    @SerializedName("raceName")
    val raceName: String,
    @SerializedName("round")
    val round: String,
    @SerializedName("season")
    val season: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("url")
    val url: String
)

fun Race.toQualiDomain():QualiDomain{
    return QualiDomain(
        circuit, qualifyingResults
    )
}
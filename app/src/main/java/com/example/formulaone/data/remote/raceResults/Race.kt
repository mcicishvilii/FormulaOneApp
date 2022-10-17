package com.example.formulaone.data.remote.raceResults

import com.example.formulaone.domain.model.remote.RaceDomain

data class Race(
    val Circuit: Circuit,
    val Results: List<Result>,
    val date: String,
    val raceName: String,
    val round: String,
    val season: String,
    val time: String,
    val url: String
)

fun Race.toRaceDomain():RaceDomain{
    return RaceDomain(
        Circuit, Results, date, raceName, round
    )
}
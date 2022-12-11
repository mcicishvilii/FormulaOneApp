package com.example.formulaoneapplicationn.data.model.raceSchedule

import com.example.formulaone.data.model.raceSchedule.*
import com.example.formulaoneapplicationn.domain.model.RaceScheduleDomain

data class Race(
    val Circuit: Circuit,
    val FirstPractice: FirstPractice,
    val Qualifying: Qualifying,
    val SecondPractice: SecondPractice,
    val Sprint: Sprint,
    val ThirdPractice: ThirdPractice,
    val date: String,
    val raceName: String,
    val round: String,
    val season: String,
    val time: String,
    val url: String

)

fun Race.ToRaceScheduleDomain(): RaceScheduleDomain {
    return RaceScheduleDomain(
        Circuit,raceName,round,date,season,time
    )
}
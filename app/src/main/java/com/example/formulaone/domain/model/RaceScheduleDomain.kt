package com.example.formulaone.domain.model

import com.example.formulaone.data.model.raceSchedule.Circuit

data class RaceScheduleDomain(
    val Circuit: Circuit,
    val raceName: String,
    val round: String,
    val date: String,
    val season: String,
    val time: String,
)
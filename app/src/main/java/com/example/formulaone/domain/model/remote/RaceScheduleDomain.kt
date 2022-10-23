package com.example.formulaone.domain.model.remote

import com.example.formulaone.data.local.models.TeamsDtoLocal
import com.example.formulaone.data.remote.raceSchedule.Circuit
import com.example.formulaone.data.remote.raceSchedule.Race
import com.example.formulaone.data.remote.raceSchedule.RaceScheduleDto
import com.example.formulaone.data.remote.raceSchedule.ThirdPractice

data class RaceScheduleDomain(
    val Circuit: Circuit,
    val raceName: String,
    val round: String,
    val date: String,
    val season: String,
//    val ThirdPractice:ThirdPractice
)
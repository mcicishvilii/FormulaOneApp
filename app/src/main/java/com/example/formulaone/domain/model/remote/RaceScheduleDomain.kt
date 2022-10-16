package com.example.formulaone.domain.model.remote

import com.example.formulaone.data.remote.raceSchedule.Circuit

class RaceScheduleDomain(
    Circuit: Circuit,
    raceName: String,
    round: String,
    date: String,
    season: String
) {
    data class TeamsDomain(
        val Circuit: Circuit,
        val raceName: String,
        val round: String,
        val date: String,
        val season: String,
    )
}


package com.example.formulaoneapplicationn.data.model.drivers.drivers_standings

import com.example.formulaoneapplicationn.data.model.drivers.drivers_standings.DriverStanding

data class StandingsLists(
    val DriverStandings: List<DriverStanding>,
    val round: String,
    val season: String
)
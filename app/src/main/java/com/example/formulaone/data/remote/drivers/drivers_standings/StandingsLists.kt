package com.example.formulaone.data.remote.drivers.drivers_standings

data class StandingsLists(
    val DriverStandings: List<DriverStanding>,
    val round: String,
    val season: String
)
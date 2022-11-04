package com.example.formulaone.data.model.drivers.drivers_standings

data class StandingsLists(
    val DriverStandings: List<com.example.formulaone.data.model.drivers.drivers_standings.DriverStanding>,
    val round: String,
    val season: String
)
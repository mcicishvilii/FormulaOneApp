package com.example.formulaone.data.remote.drivers.last_race

data class RaceTable(
    val Races: List<Race>,
    val round: String,
    val season: String
)
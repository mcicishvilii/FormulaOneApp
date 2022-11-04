package com.example.formulaone.data.model.drivers.last_race

data class RaceTable(
    val Races: List<com.example.formulaone.data.model.drivers.last_race.Race>,
    val round: String,
    val season: String
)
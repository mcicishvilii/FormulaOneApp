package com.example.formulaone.data.model.drivers.last_race

data class Race(
    val Circuit: com.example.formulaone.data.model.drivers.last_race.Circuit,
    val Results: List<com.example.formulaone.data.model.drivers.last_race.Result>,
    val date: String,
    val raceName: String,
    val round: String,
    val season: String,
    val time: String,
    val url: String
)
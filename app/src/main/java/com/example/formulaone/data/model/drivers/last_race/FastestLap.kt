package com.example.formulaone.data.model.drivers.last_race

data class FastestLap(
    val AverageSpeed: com.example.formulaone.data.model.drivers.last_race.AverageSpeed,
    val Time: com.example.formulaone.data.model.drivers.last_race.Time,
    val lap: String,
    val rank: String
)
package com.example.formulaone.data.remote.drivers.last_race

data class FastestLap(
    val AverageSpeed: AverageSpeed,
    val Time: Time,
    val lap: String,
    val rank: String
)
package com.example.formulaone.data.remote.raceResults

data class FastestLap(
    val AverageSpeed: AverageSpeed,
    val Time: Time,
    val lap: String,
    val rank: String
)
package com.example.formulaone.data.model.drivers.last_race

data class Result(
    val Constructor: com.example.formulaone.data.model.drivers.last_race.Constructor,
    val Driver: com.example.formulaone.data.model.drivers.last_race.Driver,
    val FastestLap: com.example.formulaone.data.model.drivers.last_race.FastestLap,
    val Time: com.example.formulaone.data.model.drivers.last_race.TimeX,
    val grid: String,
    val laps: String,
    val number: String,
    val points: String,
    val position: String,
    val positionText: String,
    val status: String
)
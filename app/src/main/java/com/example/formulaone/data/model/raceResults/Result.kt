package com.example.formulaone.data.model.raceResults

data class Result(
    val Constructor: Constructor,
    val Driver: Driver,
    val FastestLap: FastestLap,
    val Time: TimeX,
    val grid: String,
    val laps: String,
    val number: String,
    val points: String,
    val position: String,
    val positionText: String,
    val status: String
)
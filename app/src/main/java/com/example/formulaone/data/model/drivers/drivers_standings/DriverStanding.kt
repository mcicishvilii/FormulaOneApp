package com.example.formulaone.data.model.drivers.drivers_standings

data class DriverStanding(
    val Constructors: List<com.example.formulaone.data.model.drivers.drivers_standings.Constructor>,
    val Driver: com.example.formulaone.data.model.drivers.drivers_standings.Driver,
    val points: String,
    val position: String,
    val positionText: String,
    val wins: String
)
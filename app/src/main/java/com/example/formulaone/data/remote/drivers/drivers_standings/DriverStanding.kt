package com.example.formulaone.data.remote.drivers.drivers_standings

data class DriverStanding(
    val Constructors: List<Constructor>,
    val Driver: Driver,
    val points: String,
    val position: String,
    val positionText: String,
    val wins: String
)
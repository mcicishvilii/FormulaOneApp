package com.example.formulaoneapplicationn.domain.model

import com.example.formulaoneapplicationn.data.model.drivers.drivers_standings.Constructor
import com.example.formulaoneapplicationn.data.model.drivers.drivers_standings.Driver

data class DriverStandingDomain(
    val Constructors: List<Constructor>,
    val Driver: Driver,
    val points: String,
    val position: String,
    val positionText: String,
    val wins: String
)

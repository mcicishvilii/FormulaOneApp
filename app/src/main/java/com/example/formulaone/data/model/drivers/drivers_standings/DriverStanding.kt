package com.example.formulaoneapplicationn.data.model.drivers.drivers_standings

import com.example.formulaoneapplicationn.data.model.drivers.drivers_standings.Constructor
import com.example.formulaoneapplicationn.data.model.drivers.drivers_standings.Driver
import com.example.formulaoneapplicationn.domain.model.DriverStandingDomain

data class DriverStanding(
    val Constructors: List<Constructor>,
    val Driver: Driver,
    val points: String,
    val position: String,
    val positionText: String,
    val wins: String
)

fun DriverStanding.toDomain(): DriverStandingDomain {
    return DriverStandingDomain(
        Constructors, Driver, points, position, positionText, wins
    )
}
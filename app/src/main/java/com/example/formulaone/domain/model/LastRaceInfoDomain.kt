package com.example.formulaoneapplicationn.domain.model

import com.example.formulaoneapplicationn.data.model.last_race.Circuit
import com.example.formulaoneapplicationn.data.model.last_race.Result

data class LastRaceInfoDomain(
    val date: String = "",
    val circuit: Circuit = Circuit(),
    val raceName: String = "",
    val results: List<Result> = listOf(),
    val time: String = ""
)

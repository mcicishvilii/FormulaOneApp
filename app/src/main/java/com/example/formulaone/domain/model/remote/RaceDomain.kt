package com.example.formulaone.domain.model.remote

import com.example.formulaone.data.remote.raceResults.Circuit
import com.example.formulaone.data.remote.raceResults.Result

data class RaceDomain(
    val Circuit: Circuit,
    val Results: List<Result>,
    val date: String,
    val raceName: String,
    val round: String,
)
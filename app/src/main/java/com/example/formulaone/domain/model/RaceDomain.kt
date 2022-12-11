package com.example.formulaoneapplicationn.domain.model

import com.example.formulaone.data.model.raceResults.Circuit

data class RaceDomain(
    val Circuit: Circuit,
    val Results: List<com.example.formulaone.data.model.raceResults.Result>,
    val date: String,
    val raceName: String,
    val round: String,
)
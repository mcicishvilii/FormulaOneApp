package com.example.formulaoneapplicationn.domain.model

import com.google.gson.annotations.SerializedName

data class DailyDomain (
    val precipitationSum: List<Double> = listOf(),
    val weatherCode: List<Int> = listOf(),
    val temperature2mMax: List<Double> = listOf(),
    val time: List<String> = listOf()
)

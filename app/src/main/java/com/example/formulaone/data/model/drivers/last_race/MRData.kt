package com.example.formulaone.data.model.drivers.last_race

data class MRData(
    val RaceTable: com.example.formulaone.data.model.drivers.last_race.RaceTable,
    val limit: String,
    val offset: String,
    val series: String,
    val total: String,
    val url: String,
    val xmlns: String
)
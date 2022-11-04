package com.example.formulaone.data.model.drivers.drivers_standings

data class MRData(
    val StandingsTable: com.example.formulaone.data.model.drivers.drivers_standings.StandingsTable,
    val limit: String,
    val offset: String,
    val series: String,
    val total: String,
    val url: String,
    val xmlns: String
)
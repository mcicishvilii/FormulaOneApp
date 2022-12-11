package com.example.formulaoneapplicationn.data.model.drivers.drivers_standings

import com.example.formulaoneapplicationn.data.model.drivers.drivers_standings.StandingsLists

data class StandingsTable(
    val StandingsLists: List<StandingsLists>,
    val season: String
)
package com.example.formulaone.ui.mainFragment

import com.example.formulaone.domain.repository.LastWinningDriverRepository
import com.example.formulaone.domain.repository.TeamsRepository
import com.example.formulaone.models.drivers.plugin.PluginStandings
import com.example.formulaone.models.teams.Teams
import com.example.formulaone.network.apis.CurrentDriversStandingsApi
import javax.inject.Inject

class LastRaceWinnerTeamsRepository @Inject constructor(
    private val api: CurrentDriversStandingsApi
) : LastWinningDriverRepository {


    override suspend fun getLastWinningDriver(): PluginStandings {
        return api.getCurrentStandings()
    }


}
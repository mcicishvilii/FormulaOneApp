package com.example.formulaone.domain.repository

import com.example.formulaone.models.drivers.plugin.PluginStandings
import com.example.formulaone.models.teams.Teams

interface LastWinningDriverRepository {
    suspend fun getLastWinningDriver(): PluginStandings
}
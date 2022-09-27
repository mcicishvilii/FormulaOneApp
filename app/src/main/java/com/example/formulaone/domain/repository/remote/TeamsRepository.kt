package com.example.formulaone.domain.repository.remote

import com.example.formulaone.data.remote.teams.Teams
import com.example.formulaone.domain.model.remote.TeamsDomain

interface TeamsRepository {
    suspend fun getTeamsData(): List<TeamsDomain>
}
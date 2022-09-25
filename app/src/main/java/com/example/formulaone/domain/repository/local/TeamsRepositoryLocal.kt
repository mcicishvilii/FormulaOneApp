package com.example.formulaone.domain.repository.local

import com.example.formulaone.data.local.TeamsDtoLocal
import kotlinx.coroutines.flow.Flow

interface TeamsRepositoryLocal {

    fun getTeams(): Flow<List<TeamsDtoLocal>>

    suspend fun insertTeamIntoDb(team: TeamsDtoLocal)

    suspend fun deleteTeam(team: TeamsDtoLocal)

    suspend fun deleteAll()
}
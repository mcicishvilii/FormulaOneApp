package com.example.formulaone.domain.repository

import androidx.paging.PagingData
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.data.model.TeamsEntity
import com.example.formulaoneapplicationn.data.model.teams.Teams
import com.example.formulaoneapplicationn.domain.model.TeamsDomain
import kotlinx.coroutines.flow.Flow

interface TeamsRepository {
    suspend fun getTeamsData(): Flow<PagingData<TeamsDomain>>

    fun getTeams(): Flow<List<TeamsDomain>>

    suspend fun insertTeamIntoDb(team: TeamsDomain)

    suspend fun deleteTeam(team: TeamsEntity)

    suspend fun deleteAll()
}
package com.example.formulaone.domain.repository.local

import com.example.formulaone.data.local.TeamsDtoLocal
import com.example.formulaone.domain.model.local.TeamRoom
import com.example.formulaone.domain.model.remote.TeamsDomain
import kotlinx.coroutines.flow.Flow

interface TeamsRepositoryLocal {

    fun getTeams(): Flow<List<TeamsDomain>>

    suspend fun insertTeamIntoDb(team:TeamsDomain)

    suspend fun deleteTeam(team: TeamsDtoLocal)

    suspend fun deleteAll()
}
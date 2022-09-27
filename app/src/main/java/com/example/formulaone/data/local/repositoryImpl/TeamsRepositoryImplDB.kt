package com.example.formulaone.data.local.repositoryImpl

import com.example.formulaone.domain.repository.local.TeamsRepositoryLocal
import com.example.formulaone.data.local.TeamsDao
import com.example.formulaone.data.local.TeamsDtoLocal
import com.example.formulaone.data.local.toModel
import com.example.formulaone.domain.model.local.TeamRoom
import com.example.formulaone.domain.model.remote.TeamsDomain
import com.example.formulaone.domain.model.remote.toRoomDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TeamsRepositoryImplDB @Inject constructor(
    private val teamsDao: TeamsDao
) : TeamsRepositoryLocal {


    override fun getTeams(): Flow<List<TeamsDomain>> {
        return teamsDao.getAll().map {it.map { it.toModel() }}
    }

    override suspend fun insertTeamIntoDb(team: TeamsDomain) {
        teamsDao.insert(team.toRoomDto())
    }

    override suspend fun deleteTeam(team: TeamsDtoLocal) {
        teamsDao.delete(team)
    }

    override suspend fun deleteAll() {
        teamsDao.deleteAll()
    }

}
package com.example.formulaone.data.repository

import com.example.formulaone.data.daos.TeamsDao
import com.example.formulaone.data.model.TeamsDtoLocal
import com.example.formulaone.data.model.teams.ToTeamsDomain
import com.example.formulaone.data.model.toModel
import com.example.formulaone.domain.repository.TeamsRepository
import com.example.formulaone.domain.model.TeamsDomain
import com.example.formulaone.data.services.RaceService
import com.example.formulaone.domain.model.toRoomDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TeamsRepositoryImpl @Inject constructor(
    private val api: RaceService,
    private val teamsDao: TeamsDao
) : TeamsRepository {

    override suspend fun getTeamsData(): List<TeamsDomain> {
        return api.getDriversList().MRData.ConstructorTable.Constructors!!.map{it.ToTeamsDomain()}
    }

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
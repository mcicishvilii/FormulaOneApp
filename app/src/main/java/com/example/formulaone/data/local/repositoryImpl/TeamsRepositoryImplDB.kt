package com.example.formulaone.data.local.repositoryImpl

import com.example.formulaone.domain.repository.local.TeamsRepositoryLocal
import com.example.formulaone.data.local.TeamsDao
import com.example.formulaone.data.local.TeamsDtoLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TeamsRepositoryImplDB @Inject constructor(
    private val teamsDao: TeamsDao
) : TeamsRepositoryLocal {


    override fun getTeams(): Flow<List<TeamsDtoLocal>> {
        return teamsDao.getAll()
    }

    override suspend fun insertTeamIntoDb(team: TeamsDtoLocal) {
        teamsDao.insert(team)
    }

    override suspend fun deleteTeam(team: TeamsDtoLocal) {
        teamsDao.delete(team)
    }

    override suspend fun deleteAll() {
        teamsDao.deleteAll()
    }

}
package com.example.formulaoneapplicationn.data.repository

import com.example.formulaoneapplicationn.data.daos.TeamsDao
import com.example.formulaoneapplicationn.data.model.teams.ToTeamsDomain
import com.example.formulaoneapplicationn.data.model.toModel
import com.example.formulaone.domain.repository.TeamsRepository
import com.example.formulaone.common.Resource
import com.example.formulaoneapplicationn.data.model.TeamsEntity
import com.example.formulaoneapplicationn.domain.model.TeamsDomain
import com.example.formulaoneapplicationn.data.services.RaceService
import com.example.formulaoneapplicationn.domain.model.toRoomDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TeamsRepositoryImpl @Inject constructor(
    private val api: RaceService,
    private val teamsDao: TeamsDao
) : TeamsRepository {

    override suspend fun getTeamsData(): Flow<Resource<List<TeamsDomain>>> = flow {
        try {
            emit(Resource.Loading(true))
            val response = api.getTeams()
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.MRData.ConstructorTable.Constructors!!.map { it.ToTeamsDomain()}))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "unexpected"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    override fun getTeams(): Flow<List<TeamsDomain>> {
        return teamsDao.getAll().map {it.map { it.toModel() }}
    }

    override suspend fun insertTeamIntoDb(team: TeamsDomain) {
        teamsDao.insert(team.toRoomDto())
    }

    override suspend fun deleteTeam(team: TeamsEntity) {
        teamsDao.delete(team)
    }

    override suspend fun deleteAll() {
        teamsDao.deleteAll()
    }
}
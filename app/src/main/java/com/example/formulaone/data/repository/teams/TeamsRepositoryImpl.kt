package com.example.formulaone.data.repository.teams

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.formulaone.data.repository.news.NewsDataSource
import com.example.formulaoneapplicationn.data.daos.TeamsDao
import com.example.formulaoneapplicationn.data.model.teams.ToTeamsDomain
import com.example.formulaoneapplicationn.data.model.toModel
import com.example.formulaone.domain.repository.TeamsRepository
import com.example.formulaoneapplicationn.common.Constants
import com.example.formulaoneapplicationn.common.Resource
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

    override suspend fun getTeamsData(): Flow<PagingData<TeamsDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE_TEAMS
            ),
            pagingSourceFactory = { TeamsDataSource(api) }
        ).flow
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
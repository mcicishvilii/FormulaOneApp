package com.example.formulaone.data.repository_impl

import com.example.formulaone.domain.repository.remote.TeamsRepository
import com.example.formulaone.data.remote.teams.ToTeamsDomain
import com.example.formulaone.domain.model.remote.TeamsDomain
import com.example.formulaone.data.services.RaceService
import javax.inject.Inject

class TeamsRepositoryImpl @Inject constructor(
    private val api: RaceService
) : TeamsRepository {

    override suspend fun getTeamsData(): List<TeamsDomain> {
        return api.getDriversList().MRData.ConstructorTable.Constructors!!.map{it.ToTeamsDomain()}
    }
}
package com.example.formulaone.data.repository_impl

import android.util.Log
import com.example.formulaone.domain.repository.TeamsRepository
import com.example.formulaone.data.teams.Teams
import com.example.formulaone.network.apis.ConstructorsApi
import javax.inject.Inject

class TeamsRepositoryImpl @Inject constructor(
    private val api:ConstructorsApi
) : TeamsRepository {

    override suspend fun getTeamsData(): Teams {
        return api.getDriversList()
    }

}
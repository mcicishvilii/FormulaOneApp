package com.example.formulaone.ui.navMenuFragments.teams

import android.util.Log
import com.example.formulaone.domain.repository.TeamsRepository
import com.example.formulaone.models.teams.Teams
import com.example.formulaone.network.apis.ConstructorsApi
import javax.inject.Inject

class TeamsTeamsRepository @Inject constructor(
    private val api:ConstructorsApi
) : TeamsRepository {

    init{
        Log.d("tag","misho")
    }

    override suspend fun getTeamsData(): Teams {
        return api.getDriversList()
    }


}
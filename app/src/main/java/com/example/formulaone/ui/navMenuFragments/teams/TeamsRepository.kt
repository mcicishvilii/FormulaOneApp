package com.example.formulaone.ui.navMenuFragments.teams

import android.app.Application
import android.util.Log
import com.example.formulaone.R
import com.example.formulaone.domain.repository.Repository
import com.example.formulaone.models.teams.Teams
import com.example.formulaone.network.apis.ConstructorsApi
import javax.inject.Inject

class TeamsRepository @Inject constructor(
    private val api:ConstructorsApi
) :Repository{

    init{
        Log.d("tag","misho")
    }

    override suspend fun getTeamsData(): Teams {
        return api.getDriversList()
    }


}
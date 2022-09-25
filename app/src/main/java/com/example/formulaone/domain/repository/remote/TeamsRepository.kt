package com.example.formulaone.domain.repository.remote

import com.example.formulaone.data.remote.teams.Teams

interface   TeamsRepository {
    suspend fun getTeamsData(): Teams
}
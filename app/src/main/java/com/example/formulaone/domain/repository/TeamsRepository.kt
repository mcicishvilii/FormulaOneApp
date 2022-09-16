package com.example.formulaone.domain.repository

import com.example.formulaone.data.teams.Teams

interface   TeamsRepository {
    suspend fun getTeamsData():Teams
}
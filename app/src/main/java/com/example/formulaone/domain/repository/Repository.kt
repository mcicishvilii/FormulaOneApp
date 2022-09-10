package com.example.formulaone.domain.repository

import com.example.formulaone.models.teams.Teams

interface Repository {
    suspend fun getTeamsData():Teams
}
package com.example.formulaone.domain

import com.example.formulaone.models.teams.Teams

data class TeamsState(
    val isLoading: Boolean = false,
    val teams: Teams,
    val error: String = ""

)

package com.example.formulaoneapplicationn.domain.model

import com.example.formulaoneapplicationn.data.model.TeamsEntity

data class TeamsDomain(
    val constructorId: String,
    val name: String?,
    val nationality: String?,
    val url: String?
)

fun TeamsDomain.toRoomDto(): TeamsEntity {
    return TeamsEntity(
        teamId = constructorId,
        teamName = name,
        teamNationality = nationality,
        url = url
    )
}
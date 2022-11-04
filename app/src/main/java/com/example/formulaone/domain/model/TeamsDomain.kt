package com.example.formulaone.domain.model

import com.example.formulaone.data.model.TeamsDtoLocal

data class TeamsDomain(
    val constructorId: String,
    val name: String?,
    val nationality: String?,
    val url: String?
)

fun TeamsDomain.toRoomDto(): TeamsDtoLocal {
    return TeamsDtoLocal(
        teamId = constructorId,
        teamName = name,
        teamNationality = nationality,
        url = url
    )
}
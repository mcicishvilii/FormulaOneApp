package com.example.formulaone.domain.model

import androidx.room.ColumnInfo

data class TeamRoom(
    val teamId:Int? = null,
    val teamName:String?,
    val teamNationality:String?,
    val url:String?
)

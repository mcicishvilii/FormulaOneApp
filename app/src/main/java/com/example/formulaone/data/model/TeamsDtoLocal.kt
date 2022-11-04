package com.example.formulaone.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.formulaone.domain.model.TeamsDomain

@Entity(tableName = "gundebi")
data class TeamsDtoLocal(
    @PrimaryKey(autoGenerate = false)
    val teamId:String,
    @ColumnInfo(name="team_name")
    val teamName:String?,
    @ColumnInfo(name="team_nationality")
    val teamNationality:String?,
    @ColumnInfo(name="url")
    val url:String?

)

fun TeamsDtoLocal.toModel(): TeamsDomain {
    return TeamsDomain(
        teamId, teamName, teamNationality, url
    )
}



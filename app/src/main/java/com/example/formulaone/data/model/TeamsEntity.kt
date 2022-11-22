package com.example.formulaoneapplicationn.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.formulaoneapplicationn.domain.model.TeamsDomain

@Entity(tableName = "gundebi")
data class TeamsEntity(
    @PrimaryKey(autoGenerate = false)
    val teamId:String,
    @ColumnInfo(name="team_name")
    val teamName:String?,
    @ColumnInfo(name="team_nationality")
    val teamNationality:String?,
    @ColumnInfo(name="url")
    val url:String?

)

fun TeamsEntity.toModel(): TeamsDomain {
    return TeamsDomain(
        teamId, teamName, teamNationality, url
    )
}



package com.example.formulaone.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gundebi")
data class TeamsDtoLocal(
    @PrimaryKey(autoGenerate = true)
    val teamId:Int? = null,
    @ColumnInfo(name="team_name")
    val teamName:String,
    @ColumnInfo(name="team_nationality")
    val teamNationality:String
)

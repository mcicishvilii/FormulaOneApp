package com.example.formulaoneapplicationn.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "biletebi")
data class TicketsEntity(
    @PrimaryKey(autoGenerate = true)
    val ticketId:Int,
    val raceDay:String,
    @ColumnInfo(name="race_name")
    val raceName:String
)
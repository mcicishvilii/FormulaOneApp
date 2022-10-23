package com.example.formulaone.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "biletebi")
data class TicketsEntity(
    @PrimaryKey(autoGenerate = true)
    val ticketId:Int,
//    @ColumnInfo(name="ticket_type")
//    val ticketType:String,
//    @ColumnInfo(name="ticket_price")
//    val ticketPrice:String,
//    @ColumnInfo(name="race_date")
    val raceDay:String,
    @ColumnInfo(name="race_name")
    val raceName:String
)
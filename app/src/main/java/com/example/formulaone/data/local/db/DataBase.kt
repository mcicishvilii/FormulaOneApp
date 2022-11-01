package com.example.formulaone.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.formulaone.data.local.daos.TeamsDao
import com.example.formulaone.data.local.daos.TicketsDao
//import com.example.formulaone.data.local.models.DriversEntity
import com.example.formulaone.data.local.models.TeamsDtoLocal
import com.example.formulaone.data.local.models.TicketsEntity

@Database(entities = [TeamsDtoLocal::class,TicketsEntity::class], version = 8)

abstract class DataBase:RoomDatabase() {
    abstract val teamsDao: TeamsDao
    abstract val ticketsDao: TicketsDao
}
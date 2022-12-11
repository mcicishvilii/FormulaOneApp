package com.example.formulaoneapplicationn.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.formulaone.ui.models.Links
import com.example.formulaoneapplicationn.data.daos.LinksDao
import com.example.formulaoneapplicationn.data.daos.TeamsDao
import com.example.formulaoneapplicationn.data.daos.TicketsDao
import com.example.formulaoneapplicationn.data.model.TeamsEntity
import com.example.formulaoneapplicationn.data.model.TicketsEntity

@Database(entities = [TeamsEntity::class, TicketsEntity::class, ], version = 8)

abstract class DataBase:RoomDatabase() {
    abstract val teamsDao: TeamsDao
    abstract val ticketsDao: TicketsDao
//    abstract val linksDao: LinksDao
}
package com.example.formulaone.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.formulaone.data.local.TeamsDao
//import com.example.formulaone.data.local.models.DriversEntity
import com.example.formulaone.data.local.models.TeamsDtoLocal

@Database(entities = [TeamsDtoLocal::class,/*DriversEntity::class]*/], version = 5)
abstract class DataBase:RoomDatabase() {
    abstract val teamsDao: TeamsDao
}
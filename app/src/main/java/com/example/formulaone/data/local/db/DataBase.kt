package com.example.formulaone.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.formulaone.data.local.TeamsDao
import com.example.formulaone.data.local.TeamsDtoLocal

@Database(entities = [TeamsDtoLocal::class], version = 4)
abstract class DataBase:RoomDatabase() {
    abstract val teamsDao: TeamsDao
}
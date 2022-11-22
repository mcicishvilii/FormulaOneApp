package com.example.formulaone.di

import android.content.Context
import androidx.room.Room
import com.example.formulaoneapplicationn.data.daos.TeamsDao
import com.example.formulaoneapplicationn.data.daos.TicketsDao
import com.example.formulaoneapplicationn.data.db.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): DataBase {
        return Room.databaseBuilder(
            context,
            DataBase::class.java,"gundebi",
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTeamsDao(db: DataBase): TeamsDao {
        return db.teamsDao
    }

    @Singleton
    @Provides
    fun provideTicketsDao(db: DataBase): TicketsDao {
        return db.ticketsDao
    }
}
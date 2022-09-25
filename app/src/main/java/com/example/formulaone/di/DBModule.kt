package com.example.formulaone.di

import android.content.Context
import androidx.room.Room
import com.example.formulaone.data.local.db.DataBase
import com.example.formulaone.data.local.TeamsDao
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
    fun provideDb(@ApplicationContext context: Context):DataBase{
        return Room.databaseBuilder(
            context,
            DataBase::class.java,"gundebi"
        ).fallbackToDestructiveMigration()
            .build()

    }

    @Singleton
    @Provides
    fun provideTeamsDao(db:DataBase): TeamsDao {
        return db.teamsDao
    }
}
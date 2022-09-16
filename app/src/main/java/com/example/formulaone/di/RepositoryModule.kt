package com.example.formulaone.di

import com.example.formulaone.domain.repository.CurrentDriversStandingsRepository
import com.example.formulaone.domain.repository.TeamsRepository
import com.example.formulaone.data.repository_impl.DriverStandingsRepositoryImpl
import com.example.formulaone.data.repository_impl.TeamsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// providing repos

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    @Singleton
    abstract fun bindTeamsRepository(
        teamsRepository: TeamsRepositoryImpl
    ): TeamsRepository

    @Binds
    @Singleton
    abstract fun bindWinningDriverRepository(
        driverStandingsRepositoryImpl: DriverStandingsRepositoryImpl
    ): CurrentDriversStandingsRepository
}
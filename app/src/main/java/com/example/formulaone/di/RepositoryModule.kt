package com.example.formulaone.di

import com.example.formulaone.domain.repository.CurrentDriversStandingsRepository
import com.example.formulaone.domain.repository.TeamsRepository
import com.example.formulaone.data.repository_impl.DriverStandingsRepositoryImpl
import com.example.formulaone.data.repository_impl.LastRaceImpl
import com.example.formulaone.data.repository_impl.TeamsRepositoryImpl
import com.example.formulaone.domain.repository.LastRaceRepository
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
    // using
    abstract fun bindTeamsRepository(
        teamsRepository: TeamsRepositoryImpl
    ): TeamsRepository

    @Binds
    @Singleton
    //not using yet
    abstract fun bindWinningDriverRepository(
        driverStandingsRepositoryImpl: DriverStandingsRepositoryImpl
    ): CurrentDriversStandingsRepository

    @Binds
    @Singleton
    //using for two usecases
    abstract fun bindCircuitRepository(
        lastRaceRepoImpl: LastRaceImpl
    ): LastRaceRepository

}

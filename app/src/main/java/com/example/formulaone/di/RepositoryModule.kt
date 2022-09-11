package com.example.formulaone.di

import com.example.formulaone.domain.repository.LastWinningDriverRepository
import com.example.formulaone.domain.repository.TeamsRepository
import com.example.formulaone.ui.mainFragment.LastRaceWinnerTeamsRepository
import com.example.formulaone.ui.navMenuFragments.teams.TeamsTeamsRepository
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
        teamsRepository: TeamsTeamsRepository
    ): TeamsRepository

    @Binds
    @Singleton
    abstract fun bindWinningDriverRepository(
        lastRaceWinnerTeamsRepository: LastRaceWinnerTeamsRepository
    ): LastWinningDriverRepository
}
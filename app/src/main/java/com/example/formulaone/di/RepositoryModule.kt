package com.example.formulaone.di

import com.example.formulaone.domain.repository.Repository
import com.example.formulaone.ui.navMenuFragments.teams.TeamsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindTeamsRepository(
        teamsRepository: TeamsRepository
    ):Repository
}
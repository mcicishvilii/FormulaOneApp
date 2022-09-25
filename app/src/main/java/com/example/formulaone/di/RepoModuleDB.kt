package com.example.formulaone.di

import com.example.formulaone.data.local.repositoryImpl.TeamsRepositoryImplDB
import com.example.formulaone.domain.repository.local.TeamsRepositoryLocal
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModuleDB {

    @Binds
    @Singleton
    abstract fun provideTeamsRepo(repoImpl: TeamsRepositoryImplDB): TeamsRepositoryLocal

}
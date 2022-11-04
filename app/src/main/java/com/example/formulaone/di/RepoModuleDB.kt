package com.example.formulaone.di

import com.example.formulaone.data.repository.TicketsRepositoryImpl
import com.example.formulaone.domain.repository.TicketsRepository
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
    abstract fun provideTicketsRepo(repoImpl: TicketsRepositoryImpl): TicketsRepository
}
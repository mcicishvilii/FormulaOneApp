package com.example.formulaone.di

import com.example.formulaone.common.Constants
import com.example.formulaone.data.services.NewsService
import com.example.formulaone.data.services.RaceService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideRace(): RaceService =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RaceService::class.java)


    @Singleton
    @Provides
    fun provideNews(): NewsService =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_NEWS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
}
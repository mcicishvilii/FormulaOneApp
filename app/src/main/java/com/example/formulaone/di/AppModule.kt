package com.example.formulaone.di

import com.example.formulaone.Constants
import com.example.formulaone.data.remote.news.NewsDto
import com.example.formulaone.data.remote.raceResults.Race
import com.example.formulaone.network.apis.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideRace(): RaceApis =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RaceApis::class.java)


    @Singleton
    @Provides
    fun provideNews(): NewsApis =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_NEWS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApis::class.java)


}
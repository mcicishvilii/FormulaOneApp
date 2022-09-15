package com.example.formulaone.di

import com.example.formulaone.Constants
import com.example.formulaone.network.apis.ConstructorsApi
import com.example.formulaone.network.apis.CurrentDriversStandingsApi
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


    @Provides
    @Singleton
    fun provideConstructorsApi():ConstructorsApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ConstructorsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLastWinningDriver():CurrentDriversStandingsApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrentDriversStandingsApi::class.java)
    }

}
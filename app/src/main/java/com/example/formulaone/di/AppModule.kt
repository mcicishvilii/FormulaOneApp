package com.example.formulaone.di

import com.example.formulaone.Constants
import com.example.formulaone.network.apis.ConstructorsApi
import com.example.formulaone.network.apis.CurrentDriversStandingsApi
import com.example.formulaone.network.apis.LastRaceAPi
import com.example.formulaone.network.apis.RaceScheduleApi
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
    fun provideRetrofit():Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideConstructorsApi(retrofit: Retrofit):ConstructorsApi{
        return retrofit.create(ConstructorsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStandingsApi(retrofit: Retrofit):CurrentDriversStandingsApi{
        return retrofit.create(CurrentDriversStandingsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLastRaceInfoApi(retrofit: Retrofit):LastRaceAPi{
        return retrofit.create(LastRaceAPi::class.java)
    }


    @Provides
    @Singleton
    fun provideRacesScheduleInfo(retrofit: Retrofit):RaceScheduleApi{
        return retrofit.create(RaceScheduleApi::class.java)
    }

}
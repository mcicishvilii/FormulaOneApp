package com.example.formulaone.di


import com.example.formulaoneapplicationn.common.Constants
import com.example.formulaoneapplicationn.data.services.NewsService
import com.example.formulaoneapplicationn.data.services.RaceService
import com.example.formulaoneapplicationn.data.services.WeatherService
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

    @Singleton
    @Provides
    fun provideWeather(): WeatherService =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_WEATHER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
//
//    @Singleton
//    @Provides
//    fun provideLinks(): LinksService =
//        Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL_LINKS)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(LinksService::class.java)

}
package com.example.formulaone.di

import com.example.formulaone.Constants
import com.example.formulaone.data.remote.news.NewsDto
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
    fun provideRetrofit():Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//    @Singleton
//    @Provides
//    fun provideRetrofitForNews():Retrofit{
//        return Retrofit
//            .Builder()
//            .baseUrl(Constants.BASE_URL_NEWS)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

    @Provides
    @Singleton
    fun provideConstructorsApi(retrofit: Retrofit):RaceApis{
        return retrofit.create(RaceApis::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideNewsApi(retrofit: Retrofit):NewsApis{
//        return retrofit.create(NewsApis::class.java)
//    }

}
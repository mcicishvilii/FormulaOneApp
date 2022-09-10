package com.example.formulaone.di

import com.example.formulaone.network.RetrofitHelper
import com.example.formulaone.network.apis.ConstructorsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import java.lang.reflect.Constructor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
//    @Provides
//    @Singleton
//
//    fun provideConstructorsApi():ConstructorsApi{
//        return Retrofit.Builder()
//            .baseUrl()
//    }
}
package com.example.formulaone.di

import android.app.Application
import com.example.formulaone.Constants
import com.example.formulaone.domain.repository.Repository
import com.example.formulaone.network.RetrofitHelper
import com.example.formulaone.network.apis.ConstructorsApi
import com.example.formulaone.ui.navMenuFragments.teams.TeamsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.reflect.Constructor
import javax.inject.Named
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

}
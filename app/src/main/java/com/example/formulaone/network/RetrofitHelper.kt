package com.example.formulaone.network

import com.example.formulaone.network.apis.ConstructorsApi
import com.example.formulaone.network.apis.CurrentDriversStandingsApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val BASE_URL = "http://ergast.com/api/f1/"

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val RetrofitBuilder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    //old implementation without dependency injection
//    val constructorsService by lazy {
//        RetrofitBuilder.create(ConstructorsApi::class.java)
//    }

    val driversService by lazy {
        RetrofitBuilder.create(CurrentDriversStandingsApi::class.java)
    }

}
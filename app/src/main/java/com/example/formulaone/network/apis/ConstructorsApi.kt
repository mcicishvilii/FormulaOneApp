package com.example.formulaone.network.apis

import com.example.formulaone.data.teams.Teams
import retrofit2.http.GET

// using
interface ConstructorsApi {
    @GET("constructors.json")
    suspend fun getDriversList(
    ): Teams
}
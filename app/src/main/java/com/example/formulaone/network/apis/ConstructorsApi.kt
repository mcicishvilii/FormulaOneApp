package com.example.formulaone.network.apis

import com.example.formulaone.models.teams.Teams
import retrofit2.http.GET

interface ConstructorsApi {
    @GET("constructors.json")
    suspend fun getDriversList(
    ): Teams
}
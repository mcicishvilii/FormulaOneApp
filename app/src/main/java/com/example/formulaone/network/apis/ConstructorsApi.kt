package com.example.formulaone.network.apis

import com.example.formulaone.data.remote.teams.Teams
import retrofit2.http.GET

// using
interface ConstructorsApi {
    @GET("constructors.json?limit=211")
    suspend fun getDriversList(
    ): Teams
}
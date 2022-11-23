package com.example.formulaoneapplicationn.data.services

import com.example.formulaone.data.model.links.LinksDtoItem
import retrofit2.Response
import retrofit2.http.GET

interface LinksService {

    @GET("23be4717-1528-4e07-9438-42dfa8d7d1c3")
    suspend fun getLinks(
    ): Response<LinksDtoItem>

}
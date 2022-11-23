package com.example.formulaone.domain.repository

import com.example.formulaone.data.model.links.Article
import com.example.formulaone.domain.model.LinksDomain
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.domain.model.ArticleDomain
import kotlinx.coroutines.flow.Flow

// not using yet. for displaying the full list of current driver standings
interface LinksRepository {
    suspend fun getLinks(): Flow<Resource<List<LinksDomain>>>
}
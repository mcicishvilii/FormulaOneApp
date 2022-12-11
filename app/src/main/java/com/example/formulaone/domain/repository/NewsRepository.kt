package com.example.formulaone.domain.repository

import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.domain.model.ArticleDomain
import kotlinx.coroutines.flow.Flow

// not using yet. for displaying the full list of current driver standings
interface NewsRepository {
    suspend fun getNews(): Flow<Resource<List<ArticleDomain>>>
}
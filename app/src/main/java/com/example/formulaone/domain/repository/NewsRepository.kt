package com.example.formulaone.domain.repository

import androidx.paging.PagingData
import com.example.formulaoneapplicationn.domain.model.ArticleDomain
import kotlinx.coroutines.flow.Flow

interface NewsRepository{
    suspend fun getNews(): Flow<PagingData<ArticleDomain>>
}
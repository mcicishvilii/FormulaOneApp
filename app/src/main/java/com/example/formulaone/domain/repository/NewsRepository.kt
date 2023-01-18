package com.example.formulaone.domain.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.domain.model.ArticleDomain
import com.example.formulaoneapplicationn.domain.model.LastRaceInfoDomain
import kotlinx.coroutines.flow.Flow

interface NewsRepository{
    suspend fun getNews(): Flow<PagingData<ArticleDomain>>
}
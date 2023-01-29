package com.example.formulaone.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.formulaoneapplicationn.data.model.news.Article
import com.example.formulaoneapplicationn.domain.model.ArticleDomain
import com.example.formulaoneapplicationn.domain.model.LastRaceInfoDomain
import kotlinx.coroutines.flow.Flow

interface NewsRepository{
    fun getNews(q:String): Flow<PagingData<ArticleDomain>>
}
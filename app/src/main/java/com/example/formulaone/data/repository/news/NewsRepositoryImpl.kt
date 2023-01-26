package com.example.formulaone.data.repository.news

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.formulaone.domain.repository.NewsRepository
import com.example.formulaoneapplicationn.common.Constants.NETWORK_PAGE_SIZE
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.data.services.NewsService
import com.example.formulaoneapplicationn.domain.model.ArticleDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val api: NewsService,
):NewsRepository {

    override suspend fun getNews(): Flow<PagingData<ArticleDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { NewsDataSource(api) }
        ).flow
    }
}
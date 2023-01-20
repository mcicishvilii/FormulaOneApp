package com.example.formulaone.data.repository.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.formulaone.domain.repository.NewsRepository
import com.example.formulaoneapplicationn.data.services.NewsService
import com.example.formulaoneapplicationn.domain.model.ArticleDomain
import kotlinx.coroutines.flow.Flow
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


    companion object {
        private const val NETWORK_PAGE_SIZE = 5
    }
}
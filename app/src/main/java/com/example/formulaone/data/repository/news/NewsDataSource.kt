package com.example.formulaone.data.repository.news

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.formulaoneapplicationn.common.Constants.API_KEY
import com.example.formulaoneapplicationn.common.Constants.NETWORK_PAGE_SIZE
import com.example.formulaoneapplicationn.common.Constants.STARTING_PAGE_INDEX
import com.example.formulaoneapplicationn.data.model.news.toArticleDomain
import com.example.formulaoneapplicationn.data.services.NewsService
import com.example.formulaoneapplicationn.domain.model.ArticleDomain
import java.io.IOException


class NewsDataSource(private val api: NewsService) : PagingSource<Int, ArticleDomain>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDomain> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = api.getNews("formula 1+autosport+FIA",page,1, API_KEY)
            val articles = response.body()!!.articles.map { it.toArticleDomain() }

            val nextKey =
                if (articles.isEmpty()) {
                    null
                } else {
                    page + (params.loadSize / NETWORK_PAGE_SIZE)
                }

            LoadResult.Page(
                data = articles,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page,
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            val error = IOException("Please Check Internet Connection")
            LoadResult.Error(error)
        } catch (exception: retrofit2.HttpException) {
            LoadResult.Error(exception)
        }catch (e:Exception){
            LoadResult.Error(e.cause!!)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, ArticleDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
package com.example.formulaone.data.repository_impl

import com.example.formulaone.data.remote.news.new_api.toArticleDomain
import com.example.formulaone.domain.model.remote.ArticleDomain
import com.example.formulaone.domain.repository.remote.NewsRepository
import com.example.formulaone.network.apis.NewsApis
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApis
) : NewsRepository {

    override suspend fun getNews(): List<ArticleDomain> {
        val response = api.getNews(
            "formula 1",
            "6ce7c585fe714572ad745ea44c378403"
        )
        return if (response.isSuccessful) {
            response.body()?.articles!!.map{it.toArticleDomain() }
        } else {
            emptyList()
        }
    }
}

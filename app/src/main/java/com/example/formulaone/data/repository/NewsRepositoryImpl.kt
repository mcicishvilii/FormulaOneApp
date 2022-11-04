package com.example.formulaone.data.repository

import com.example.formulaone.data.model.news.new_api.toArticleDomain
import com.example.formulaone.domain.model.ArticleDomain
import com.example.formulaone.domain.repository.NewsRepository
import com.example.formulaone.data.services.NewsService
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsService
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

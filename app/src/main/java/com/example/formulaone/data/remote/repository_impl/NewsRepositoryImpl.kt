package com.example.formulaone.data.remote.repository_impl

import com.example.formulaone.domain.repository.remote.CurrentDriversStandingsRepository
import com.example.formulaone.data.remote.drivers.drivers_standings.DriverStandingsDto
import com.example.formulaone.data.remote.news.NewsDto
import com.example.formulaone.data.remote.news.new_api.F1NewsDto
import com.example.formulaone.data.remote.news.new_api.toArticleDomain
import com.example.formulaone.domain.model.remote.ArticleDomain
import com.example.formulaone.domain.repository.remote.NewsRepository
import com.example.formulaone.network.apis.NewsApis
import com.example.formulaone.network.apis.RaceApis
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApis
) : NewsRepository {
    override suspend fun getNews(): List<ArticleDomain> {
        return api.getNews(
            "formula 1",
            "6ce7c585fe714572ad745ea44c378403"
        ).articles.map { it.toArticleDomain() }
    }
}

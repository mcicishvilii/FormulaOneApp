package com.example.formulaone.data.remote.repository_impl

import com.example.formulaone.domain.repository.remote.CurrentDriversStandingsRepository
import com.example.formulaone.data.remote.drivers.drivers_standings.DriverStandingsDto
import com.example.formulaone.data.remote.news.NewsDto
import com.example.formulaone.domain.repository.remote.NewsRepository
import com.example.formulaone.network.apis.NewsApis
import com.example.formulaone.network.apis.RaceApis
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApis
) : NewsRepository {
    override suspend fun getNews(): List<NewsDto> {
        return api.getNews("f1")
    }
}
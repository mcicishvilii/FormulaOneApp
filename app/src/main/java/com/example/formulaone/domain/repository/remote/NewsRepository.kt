package com.example.formulaone.domain.repository.remote

import com.example.formulaone.data.remote.drivers.drivers_standings.DriverStandingsDto
import com.example.formulaone.data.remote.news.NewsDto
import com.example.formulaone.data.remote.news.new_api.F1NewsDto
import com.example.formulaone.domain.model.remote.ArticleDomain
import com.example.formulaone.domain.model.remote.NewsDtItemDomain

// not using yet. for displaying the full list of current driver standings
interface NewsRepository {
    suspend fun getNews(): List<ArticleDomain>
}
package com.example.formulaoneapplicationn.domain.model

import com.example.formulaoneapplicationn.data.model.news.Source

data class ArticleDomain(
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    val title: String? = "",
    val urlToImage: String? = "",
    val url: String? = "",
    val source: Source
)

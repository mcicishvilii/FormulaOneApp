package com.example.formulaone.domain.model.remote

data class ArticleDomain(
    val content: String = "",
    val description: String = "",
    val publishedAt: String = "",
    val title: String = "",
    val urlToImage: String? = ""
)

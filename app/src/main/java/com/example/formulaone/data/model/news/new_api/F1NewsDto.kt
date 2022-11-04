package com.example.formulaone.data.model.news.new_api


import com.google.gson.annotations.SerializedName

data class F1NewsDto(
    @SerializedName("articles")
    val articles: List<Article> = listOf(),
    @SerializedName("status")
    val status: String = "",
    @SerializedName("totalResults")
    val totalResults: Int = 0
)
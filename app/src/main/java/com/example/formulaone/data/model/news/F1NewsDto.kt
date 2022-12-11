package com.example.formulaoneapplicationn.data.model.news


import com.google.gson.annotations.SerializedName

data class F1NewsDto(
    @SerializedName("articles")
    val articles: List<Article> = listOf(),
    @SerializedName("status")
    val status: String = "",
    @SerializedName("totalResults")
    val totalResults: Int = 0
)
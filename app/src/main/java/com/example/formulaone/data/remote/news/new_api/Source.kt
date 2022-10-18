package com.example.formulaone.data.remote.news.new_api


import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String = ""
)
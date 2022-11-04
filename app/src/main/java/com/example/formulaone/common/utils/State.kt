package com.example.formulaone.common.utils

import com.example.formulaone.data.model.TeamsDtoLocal


data class State<T>(
    val loading: Boolean = false,
    val data: List<TeamsDtoLocal> = emptyList(),
    val error: String = ""
)

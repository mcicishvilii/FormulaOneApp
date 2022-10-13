package com.example.formulaone.ui.models

import android.view.View
import com.example.formulaone.adapters.settings.HasButton

data class Settings(
    val userName:String,
    val subscribe:String,
    val hasButton:HasButton
)

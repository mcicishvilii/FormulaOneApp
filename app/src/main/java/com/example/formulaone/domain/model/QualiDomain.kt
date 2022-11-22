package com.example.formulaone.domain.model

import com.example.formulaone.data.model.drivers.quali.Circuit
import com.example.formulaone.data.model.drivers.quali.QualifyingResult

data class QualiDomain(
    val circuit: Circuit,
    val qualifyingResults: List<QualifyingResult>,
)

package com.example.formulaone.domain.use_case

data class UseCasesWrapper(
    val getTeamsListUseCase: GetTeamsListUseCase,
    val currentDriversStandingsUseCase: CurrentDriversStandingsUseCase
)
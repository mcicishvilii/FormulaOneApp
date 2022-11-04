package com.example.formulaone.domain.use_case.teams

import com.example.formulaone.domain.repository.TeamsRepository
import javax.inject.Inject

class DeleteAllUseCase @Inject constructor(
    private val repository: TeamsRepository
) {
    suspend operator fun invoke() {
        repository.deleteAll()
    }
}
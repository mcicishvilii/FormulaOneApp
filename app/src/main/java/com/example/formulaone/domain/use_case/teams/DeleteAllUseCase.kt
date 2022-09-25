package com.example.formulaone.domain.use_case.teams

import com.example.formulaone.domain.repository.local.TeamsRepositoryLocal
import javax.inject.Inject

class DeleteAllUseCase @Inject constructor(
    private val repository: TeamsRepositoryLocal
) {
    suspend operator fun invoke() {
        repository.deleteAll()
    }
}
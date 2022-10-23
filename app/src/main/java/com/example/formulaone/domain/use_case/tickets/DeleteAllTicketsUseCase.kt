package com.example.formulaone.domain.use_case.tickets

import com.example.formulaone.domain.repository.local.TicketsRepositoryLocal
import javax.inject.Inject

class DeleteAllTicketsUseCase @Inject constructor(
    private val repository: TicketsRepositoryLocal
) {
    suspend operator fun invoke() {
        repository.deleteAll()
    }
}
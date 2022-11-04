package com.example.formulaone.domain.use_case.tickets

import com.example.formulaone.domain.repository.TicketsRepository
import javax.inject.Inject

class DeleteAllTicketsUseCase @Inject constructor(
    private val repository: TicketsRepository
) {
    suspend operator fun invoke() {
        repository.deleteAll()
    }
}
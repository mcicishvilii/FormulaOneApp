package com.example.formulaone.domain.use_case.tickets

import com.example.formulaone.data.model.TicketsEntity
import com.example.formulaone.domain.repository.TicketsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetTicketsListUseCase @Inject constructor(
    private val repository: TicketsRepository
) {
    operator fun invoke(): Flow<List<TicketsEntity>> {
        return repository.getTickets()
    }
}
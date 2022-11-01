package com.example.formulaone.domain.use_case.tickets

import com.example.formulaone.data.local.models.TicketsEntity
import com.example.formulaone.domain.repository.local.TicketsRepositoryLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetTicketsListUseCase @Inject constructor(
    private val repository: TicketsRepositoryLocal
) {
    operator fun invoke(): Flow<List<TicketsEntity>> {
        return repository.getTickets()
    }
}
package com.example.formulaone.domain.use_case.tickets

import com.example.formulaone.domain.repository.TicketsRepository
import com.example.formulaoneapplicationn.data.model.TicketsEntity
import javax.inject.Inject

class DeleteTicketUseCase @Inject constructor(
    private val repository: TicketsRepository
) {
    suspend operator fun invoke(ticket: TicketsEntity) {
        repository.deleteTicket(ticket)
    }
}
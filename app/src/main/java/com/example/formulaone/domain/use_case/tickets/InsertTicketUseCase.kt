package com.example.formulaone.domain.use_case.tickets

import com.example.formulaone.data.local.models.TicketsEntity
import com.example.formulaone.domain.repository.local.TeamsRepositoryLocal
import com.example.formulaone.domain.model.remote.TeamsDomain
import com.example.formulaone.domain.repository.local.TicketsRepositoryLocal
import javax.inject.Inject

class InsertTicketUseCase @Inject constructor(
    private val repository: TicketsRepositoryLocal
) {
    suspend operator fun invoke(ticket: TicketsEntity) {
        repository.insertTicket(ticket)
    }
}
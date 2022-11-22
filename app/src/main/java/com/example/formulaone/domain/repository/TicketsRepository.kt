package com.example.formulaone.domain.repository

import com.example.formulaoneapplicationn.data.model.TicketsEntity
import kotlinx.coroutines.flow.Flow

interface TicketsRepository {

    fun getTickets(): Flow<List<TicketsEntity>>

    suspend fun insertTicket(ticket: TicketsEntity)

    suspend fun deleteTicket(ticket: TicketsEntity)

    suspend fun deleteAll()
}
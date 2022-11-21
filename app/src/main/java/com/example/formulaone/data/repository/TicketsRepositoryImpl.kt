package com.example.formulaoneapplicationn.data.repository

import com.example.formulaoneapplicationn.data.daos.TicketsDao
import com.example.formulaoneapplicationn.data.model.TicketsEntity
import com.example.formulaone.domain.repository.TicketsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TicketsRepositoryImpl @Inject constructor(
    private val ticketsDao: TicketsDao
) : TicketsRepository {
    override fun getTickets(): Flow<List<TicketsEntity>>{
        return ticketsDao.getAll()
    }

    override suspend fun insertTicket(ticket: TicketsEntity) {
        ticketsDao.insert(ticket)
    }

    override suspend fun deleteTicket(ticket: TicketsEntity) {
        ticketsDao.delete(ticket)
    }

    override suspend fun deleteAll() {
        ticketsDao.deleteAll()
    }
}
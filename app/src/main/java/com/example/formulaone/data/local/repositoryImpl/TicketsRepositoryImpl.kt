package com.example.formulaone.data.local.repositoryImpl

import com.example.formulaone.domain.repository.local.TeamsRepositoryLocal
import com.example.formulaone.data.local.daos.TeamsDao
import com.example.formulaone.data.local.daos.TicketsDao
import com.example.formulaone.data.local.models.TeamsDtoLocal
import com.example.formulaone.data.local.models.TicketsEntity
import com.example.formulaone.data.local.models.toModel
import com.example.formulaone.domain.model.remote.TeamsDomain
import com.example.formulaone.domain.model.remote.toRoomDto
import com.example.formulaone.domain.repository.local.TicketsRepositoryLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TicketsRepositoryImpl @Inject constructor(
    private val ticketsDao: TicketsDao
) : TicketsRepositoryLocal {
    override fun getTickets(): Flow<TicketsEntity>{
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
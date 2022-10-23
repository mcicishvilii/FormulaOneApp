package com.example.formulaone.domain.repository.local

import com.example.formulaone.data.local.models.TeamsDtoLocal
import com.example.formulaone.data.local.models.TicketsEntity
import com.example.formulaone.domain.model.local.TeamRoom
import com.example.formulaone.domain.model.remote.TeamsDomain
import kotlinx.coroutines.flow.Flow

interface TicketsRepositoryLocal {

    fun getTickets(): Flow<TicketsEntity>

    suspend fun insertTicket(team:TicketsEntity)

    suspend fun deleteTicket(team: TicketsEntity)

    suspend fun deleteAll()
}
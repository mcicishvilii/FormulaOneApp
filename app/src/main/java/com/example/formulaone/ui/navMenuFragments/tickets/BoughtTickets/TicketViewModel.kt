package com.example.formulaone.ui.navMenuFragments.tickets.BoughtTickets

import androidx.lifecycle.ViewModel
import com.example.formulaone.data.local.models.TicketsEntity
import com.example.formulaone.domain.use_case.tickets.GetTicketsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val getTicketsList: GetTicketsListUseCase,
) : ViewModel(){

    suspend fun getTicket(): Flow<List<TicketsEntity>> {
        return getTicketsList()
    }
}
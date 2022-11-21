package com.example.formulaone.ui.navMenuFragments.tickets.BoughtTickets

import androidx.lifecycle.ViewModel
import com.example.formulaone.domain.use_case.tickets.DeleteAllTicketsUseCase
import com.example.formulaone.domain.use_case.tickets.DeleteTicketUseCase
import com.example.formulaone.domain.use_case.tickets.GetTicketsListUseCase
import com.example.formulaoneapplicationn.data.model.TicketsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val getTicketsList: GetTicketsListUseCase,
    private val deleteAllTicketsUseCase: DeleteAllTicketsUseCase,
    private val deleteTicketUseCase: DeleteTicketUseCase,
) : ViewModel(){

    suspend fun getTicket(): Flow<List<TicketsEntity>> {
        return getTicketsList()
    }

    fun deleteTicket(ticket:TicketsEntity){
        CoroutineScope(Dispatchers.IO).launch {
            deleteTicketUseCase(ticket)
        }
    }


}
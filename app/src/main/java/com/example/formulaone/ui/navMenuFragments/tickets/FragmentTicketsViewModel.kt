package com.example.formulaone.ui.navMenuFragments.tickets

import androidx.lifecycle.ViewModel
import com.example.formulaone.domain.use_case.tickets.DeleteAllTicketsUseCase
import com.example.formulaone.domain.use_case.tickets.DeleteTicketUseCase
import com.example.formulaone.domain.use_case.tickets.GetTicketsListUseCase
import com.example.formulaone.domain.use_case.tickets.InsertTicketUseCase
import com.example.formulaoneapplicationn.data.model.TicketsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FragmentTicketsViewModel @Inject constructor(
    private val getTicketsList: GetTicketsListUseCase,
    private val deleteAllUseCase: DeleteAllTicketsUseCase,
    private val deleteTicketUseCase: DeleteTicketUseCase,
    private val insertTicketUseCase: InsertTicketUseCase,

    ) : ViewModel() {

    fun insertTicket(ticket: TicketsEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            insertTicketUseCase(ticket)
        }
    }

    fun deleteTicket(){
        CoroutineScope(Dispatchers.IO).launch {
            deleteAllUseCase.invoke()
        }
    }

}



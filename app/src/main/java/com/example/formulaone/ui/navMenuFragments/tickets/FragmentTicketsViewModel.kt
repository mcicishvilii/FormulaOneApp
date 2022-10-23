package com.example.formulaone.ui.navMenuFragments.tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.Resource
import com.example.formulaone.data.local.models.TicketsEntity
import com.example.formulaone.domain.model.remote.TeamsDomain
import com.example.formulaone.domain.use_case.teams.DeleteAllUseCase
import com.example.formulaone.domain.use_case.teams.GetTeamsListUseCase
import com.example.formulaone.domain.use_case.teams.InsertTeamUseCase
import com.example.formulaone.domain.use_case.tickets.DeleteAllTicketsUseCase
import com.example.formulaone.domain.use_case.tickets.DeleteTicketUseCase
import com.example.formulaone.domain.use_case.tickets.GetTicketsListUseCase
import com.example.formulaone.domain.use_case.tickets.InsertTicketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FragmentTicketsViewModel @Inject constructor(
    private val getTicketsList: GetTicketsListUseCase,
    private val deleteAllUseCase: DeleteAllTicketsUseCase,
    private val deleteTicketUseCase: DeleteTicketUseCase,
    private val insertTicketUseCase: InsertTicketUseCase,

    ) : ViewModel() {

//    private val _state = MutableStateFlow<Resource<TicketsEntity>>(Resource.Loading(false))
//    val state = _state.asStateFlow()


    suspend fun getTicket(): Flow<TicketsEntity> {
        return getTicketsList()
    }

    fun insertTicket(ticket: TicketsEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            insertTicketUseCase(ticket)
        }
    }

}


//fun listenForMessages() {
//    merge(userSentMessages, messagesNotifications)
//        .onEach { displayMessage(it) }
//        .launchIn(scope)
//}
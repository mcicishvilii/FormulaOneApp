package com.example.formulaone.ui.navMenuFragments.tickets

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.R
import com.example.formulaone.adapters.TicketsAdapter
import com.example.formulaone.adapters.UpcomingRaceAdapter
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.data.local.Tickets
import com.example.formulaone.data.local.models.TicketsEntity
import com.example.formulaone.databinding.FragmentFragmentTicketsBinding
import com.example.formulaone.ui.navMenuFragments.schedule.upcoming.UpcomingRacesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentTickets : BaseFragment<FragmentFragmentTicketsBinding>(FragmentFragmentTicketsBinding::inflate) {

    val args:FragmentTicketsArgs by navArgs()

    private val ticketsAdapter: TicketsAdapter by lazy { TicketsAdapter() }
    private val ticketsViewModel: FragmentTicketsViewModel by viewModels()

    val ticketsList = mutableListOf<Tickets>()

    override fun viewCreated() {
        popTicketsList()
        setupRecycler()
        ticketsAdapter.submitList(ticketsList)

        val ticketInfo = args.ticketInfo

        binding.tvRaceDate.text = ticketInfo?.date
        binding.tvTrackName.text = ticketInfo?.trackName


    }

    override fun listeners() {
        ticketsAdapter.setOnItemClickListener{ticket,_->
            insertTicket()
        }
    }

    private fun insertTicket(){
        val ticket = TicketsEntity(
            0,
            args.ticketInfo!!.date,
            args.ticketInfo!!.trackName

        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                ticketsViewModel.insertTicket(ticket)
            }
        }
    }


    private fun setupRecycler() {
        binding.rvTickets.apply {
            adapter = ticketsAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }

    private fun popTicketsList(){
        ticketsList.add(
            Tickets(
                1,
                "Paddock Club",
                "5000 EUR",
                true
            )
        )
        ticketsList.add(
            Tickets(
                2,
                "Main Grandstand",
                "3000 EUR",
                false
            )
        )
        ticketsList.add(
            Tickets(
                3,
                "Grandstand 4",
                "1300 EUR",
                false
            )
        )

    }

}
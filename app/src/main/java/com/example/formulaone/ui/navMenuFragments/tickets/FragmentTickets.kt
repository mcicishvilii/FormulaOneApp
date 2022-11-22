package com.example.formulaone.ui.navMenuFragments.tickets

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentFragmentTicketsBinding
import com.example.formulaone.ui.adapters.TicketsAdapter
import com.example.formulaoneapplicationn.common.bases.BaseFragment
import com.example.formulaoneapplicationn.data.model.Tickets
import com.example.formulaoneapplicationn.data.model.TicketsEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentTickets :
    BaseFragment<FragmentFragmentTicketsBinding>(FragmentFragmentTicketsBinding::inflate) {
    val args: FragmentTicketsArgs by navArgs()
    private val ticketsAdapter: TicketsAdapter by lazy { TicketsAdapter() }
    private val ticketsViewModel: FragmentTicketsViewModel by viewModels()


    val ticketsList = mutableListOf<Tickets>()


    override fun viewCreated() {
        popTicketsList()
        setupRecycler()

        ticketsAdapter.submitList(ticketsList)

        binding.tvOptionsAvailable.text = "${ticketsList.size} options available"

        val ticketInfo = args.ticketInfo
        binding.tvRaceDate.text = ticketInfo?.date
        binding.tvTrackName.text = ticketInfo?.trackName
    }


    override fun listeners() {
        ticketsAdapter.apply {
            setOnItemClickListener { tickets, i ->
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("Are you sure you want to buy ${tickets.ticketType} ticket?")
                builder.setPositiveButton(R.string.yes_sure) { dialog, which ->
                    insertTicket()
                    Toast.makeText(requireContext(), R.string.you_bought_ticket, Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(requireContext(), R.string.canceled, Toast.LENGTH_SHORT).show()
                }
                builder.show()
            }
        }
    }

    fun insertTicket() {
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

    private fun popTicketsList() {
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

        ticketsList.add(
            Tickets(
                4,
                "Grandstand 3",
                "1100 EUR",
                false
            )
        )

        ticketsList.add(
            Tickets(
                5,
                "Grandstand 2",
                "1000 EUR",
                true
            )
        )

        ticketsList.add(
            Tickets(
                6,
                "Grandstand 1",
                "700 EUR",
                true
            )
        )


    }
}
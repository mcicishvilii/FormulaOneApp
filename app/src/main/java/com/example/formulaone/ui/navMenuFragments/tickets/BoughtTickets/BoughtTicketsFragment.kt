package com.example.formulaone.ui.navMenuFragments.tickets

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.ui.adapters.TicketsAdapter
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.data.local.Tickets
import com.example.formulaone.data.local.models.TicketsEntity
import com.example.formulaone.databinding.FragmentFragmentTicketsBinding
import com.example.formulaone.databinding.FragmentTicketBinding
import com.example.formulaone.ui.adapters.SchedulesAdapter.BoughtTIcketsAdapter
import com.example.formulaone.ui.navMenuFragments.tickets.BoughtTickets.TicketViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BoughtTicketsFragment : BaseFragment<FragmentTicketBinding>(FragmentTicketBinding::inflate) {


    private val ticketsAdapter: BoughtTIcketsAdapter by lazy { BoughtTIcketsAdapter() }
    private val ticketsViewModel: TicketViewModel by viewModels()


    override fun viewCreated() {
        setupRecycler()
        getTickets()
    }

    override fun listeners() {


    }


    private fun getTickets(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                ticketsViewModel.getTicket()
            }
        }
    }

    private fun setupRecycler() {
        binding.rvTickets.apply {
            adapter = ticketsAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
        }
    }

}
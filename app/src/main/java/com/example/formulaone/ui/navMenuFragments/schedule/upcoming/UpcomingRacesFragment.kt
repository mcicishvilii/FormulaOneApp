package com.example.formulaone.ui.navMenuFragments.schedule.upcoming

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.common.Resource
import com.example.formulaone.adapters.UpcomingRaceAdapter
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.databinding.FragmentUpcomingRacesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpcomingRacesFragment : BaseFragment<FragmentUpcomingRacesBinding>(FragmentUpcomingRacesBinding::inflate) {
    private val upcomingRaceAdapter: UpcomingRaceAdapter by lazy { UpcomingRaceAdapter() }
    private val upcomingRacesViewModel: UpcomingRacesViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun viewCreated() {
        observe()
    }

    override fun listeners() {
        buyTicket()
        checkBoughtTickets()
    }


    private fun checkBoughtTickets(){
        binding.ivCart.setOnClickListener {
            findNavController().navigate(UpcomingRacesFragmentDirections.actionUpcomingRacesFragmentToTicket())
        }
    }

    private fun buyTicket(){
        upcomingRaceAdapter.apply {
            setOnItemClickListener{ venue,_ ->
                findNavController().navigate(UpcomingRacesFragmentDirections.actionUpcomingRacesFragmentToFragmentTickets(
                    TicketInfo(
                        trackName = venue.raceName,
                        date = venue.date
                    )
                ))
//                Snackbar.make(binding.root,
//                    "you bought ticket for ${venue.raceName}",
//                    Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecycler() {
        binding.rvUpcomingRace.apply {
            adapter = upcomingRaceAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun observe() {
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                upcomingRacesViewModel.state.collectLatest {
                    when (it) {
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            upcomingRaceAdapter.submitList(it.data)
                            Log.d("sia",it.data.toString())
                        }
                    }
                }
            }
        }
    }

}
package com.example.formulaone.ui.navMenuFragments.schedule.upcoming

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.R
import com.example.formulaone.Resource
import com.example.formulaone.adapters.ConstructorsAdapter
import com.example.formulaone.adapters.UpcomingRaceAdapter
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.databinding.FragmentUpcomingRacesBinding
import com.example.formulaone.ui.navMenuFragments.drivers.DriversDetails
import com.example.formulaone.ui.navMenuFragments.drivers.list.DriversFragmentDirections
import com.example.formulaone.ui.navMenuFragments.teams.TeamsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

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
    }

    private fun buyTicket(){
        upcomingRaceAdapter.apply {
            setOnItemClickListener{ venue,_ ->
                Snackbar.make(binding.root,
                    "you bought ticket for ${venue.raceName}",
                    Snackbar.LENGTH_SHORT).show()
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
                            val filteredList = it.data.filter {
                                val time = Calendar.getInstance().time
                                val formatterCurrentTime = SimpleDateFormat("yyyy-MM-dd")
                                val formatterNow = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                                val currentTime = formatterCurrentTime.format(time)
                                val dateNow = LocalDate.parse(currentTime, formatterNow)

                                val dateFromModel = it.date
                                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                                val date = LocalDate.parse(dateFromModel, formatter)

                                dateNow < date
                            }
                            upcomingRaceAdapter.submitList(filteredList)
                        }
                    }
                }
            }
        }
    }

}
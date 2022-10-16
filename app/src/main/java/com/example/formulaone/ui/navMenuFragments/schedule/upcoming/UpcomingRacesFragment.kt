package com.example.formulaone.ui.navMenuFragments.schedule.upcoming

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.example.formulaone.ui.navMenuFragments.teams.TeamsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UpcomingRacesFragment : BaseFragment<FragmentUpcomingRacesBinding>(FragmentUpcomingRacesBinding::inflate) {
    private val upcomingRaceAdapter: UpcomingRaceAdapter by lazy { UpcomingRaceAdapter() }
    private val viewModel: UpcomingRacesViewModel by viewModels()
    override fun viewCreated() {
        observe()

    }

    override fun listeners() {

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
    private fun observe() {
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest {
                    when (it) {
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            constructorsAdapter.submitList(it.data)
                        }
                    }
                }
            }
        }
    }

}
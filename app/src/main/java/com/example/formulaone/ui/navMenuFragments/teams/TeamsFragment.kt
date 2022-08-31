package com.example.formulaone.ui.navMenuFragments.teams

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.ConstructorsAdapter
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentTeamsBinding
import com.example.formulaone.ui.BaseFragment
import com.example.formulaone.ui.mainFragment.MainViewModel

class TeamsFragment : BaseFragment<FragmentTeamsBinding>(FragmentTeamsBinding::inflate) {


    private val constructorsAdapter: ConstructorsAdapter by lazy { ConstructorsAdapter() }
    private val teamsViewModel: TeamsViewModel by viewModels()
    override fun viewCreated() {

        setupRecycler()
        teamsViewModel.getPopularMoviesLiveData()
        teamsViewModel.getPopularMoviesLiveData().observe(viewLifecycleOwner){
            constructorsAdapter.submitList(it)
        }
    }

    override fun listeners() {

    }


    private fun setupRecycler() {
        binding.rvStories.apply {
            adapter = constructorsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false)
        }
    }

}
package com.example.formulaone.ui.navMenuFragments.teams

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.ConstructorsAdapter
import com.example.formulaone.R
import com.example.formulaone.Resource
import com.example.formulaone.databinding.FragmentTeamsBinding
import com.example.formulaone.ui.BaseFragment
import com.example.formulaone.ui.mainFragment.MainViewModel
import com.example.formulaone.ui.navMenuFragments.drivers.DriversViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TeamsFragment : BaseFragment<FragmentTeamsBinding>(FragmentTeamsBinding::inflate) {

    private val constructorsAdapter: ConstructorsAdapter by lazy { ConstructorsAdapter() }
    private val teamsViewModel: TeamsViewModel by viewModels()

    override fun viewCreated() {
        observe()

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

    private fun observe(){
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                teamsViewModel.state.collectLatest {
                    when(it){
                        is Resource.Error -> {


                        }
                        is Resource.Loading -> {
                            binding
                        }
                        is Resource.Success -> {
                            constructorsAdapter.submitList(it.data.MRData.ConstructorTable.Constructors)
                        }
                    }
                }
            }
        }
    }

}
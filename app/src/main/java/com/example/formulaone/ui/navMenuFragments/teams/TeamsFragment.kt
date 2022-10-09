package com.example.formulaone.ui.navMenuFragments.teams

import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.adapters.ConstructorsAdapter
import com.example.formulaone.R
import com.example.formulaone.Resource
import com.example.formulaone.databinding.FragmentTeamsBinding
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.data.remote.teams.Teams
import com.example.formulaone.domain.model.remote.TeamsDomain
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class TeamsFragment : BaseFragment<FragmentTeamsBinding>(FragmentTeamsBinding::inflate) {

    private val constructorsAdapter: ConstructorsAdapter by lazy { ConstructorsAdapter() }
    private val viewModel: TeamsViewModel by viewModels()
    private var filteredList = (mutableListOf<TeamsDomain>())
    private var Bitch = (mutableListOf<TeamsDomain>())


    override fun viewCreated() {
        observe()
        search()
    }

    override fun listeners() {
        addToFavourites()
        binding.tvTeamListHeader.setOnClickListener {
            findNavController().navigate(R.id.favoritesFragment)
        }
    }

    private fun addToFavourites() {
        constructorsAdapter.apply {
            setOnItemClickListener { team, _ ->
                viewModel.insertTeam(team)
                Toast.makeText(requireContext(), "${team.name} added to db", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setupRecycler() {
        binding.rvStories.apply {
            adapter = constructorsAdapter
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


    private fun search() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query.isNullOrEmpty()){
                    poplist()
                    constructorsAdapter.submitList(Bitch)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText.isNullOrEmpty()){
                    constructorsAdapter.submitList(constructorsAdapter.currentList)
                }else{
                    for (team in constructorsAdapter.currentList){
                        if(newText == team.nationality){
                            filteredList.add(team)
                            constructorsAdapter.submitList(filteredList)
                        }
                    }
                }
                return false
            }
        })
    }

    private fun poplist(){
        Bitch.add(
            TeamsDomain(
                "misho",
                "misho",
                "misho",
                "misho",
            )
        )
    }

}

package com.example.formulaone.ui.navMenuFragments.teams

import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentTeamsBinding
import com.example.formulaone.ui.adapters.ConstructorsAdapter
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.common.bases.BaseFragment
import com.example.formulaoneapplicationn.domain.model.TeamsDomain
import com.squareup.moshi.subtypeOf
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TeamsFragment : BaseFragment<FragmentTeamsBinding>(FragmentTeamsBinding::inflate) {

    private val constructorsAdapter: ConstructorsAdapter by lazy { ConstructorsAdapter() }
    private val viewModel: TeamsViewModel by viewModels()


    override fun viewCreated() {
        observe()
//        search()
    }

    override fun listeners() {
        addToFavourites()
        binding.btnFavs.setOnClickListener {
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
                viewModel.getTeams()
                viewModel.state.collectLatest {
                    constructorsAdapter.submitData(it)
                }
            }
        }
    }
}
package com.example.formulaone.ui.favorites

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.ConstructorsAdapter
import com.example.formulaone.R
import com.example.formulaone.Resource
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.databinding.FragmentFavoritesBinding
import com.example.formulaone.ui.navMenuFragments.teams.TeamsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate) {

    private val favsAdapter: ConstructorsAdapter by lazy { ConstructorsAdapter() }
    private val viewModel: FavoritesViewModel by viewModels()

    override fun viewCreated() {
        getTeams()
    }

    override fun listeners() {

    }

    fun getTeams(){
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getTeam().collectLatest {
                    favsAdapter.submitList(it)
                }
            }
        }

    }

    private fun setupRecycler() {
        binding.rvFavTeams.apply {
            adapter = favsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false)
        }
    }



}
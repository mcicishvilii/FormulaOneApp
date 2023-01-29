package com.example.formulaone.ui.favorites

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.databinding.FragmentFavoritesBinding
import com.example.formulaone.ui.adapters.ConstructorsAdapter
import com.example.formulaone.ui.adapters.FavsAdapter
import com.example.formulaoneapplicationn.common.bases.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate) {

    private val favsAdapter: FavsAdapter by lazy { FavsAdapter() }
    private val viewModel: FavoritesViewModel by viewModels()

    override fun viewCreated() {
        getTeams()
    }

    override fun listeners() {

    }

    private fun getTeams(){
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
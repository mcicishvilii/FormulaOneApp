package com.example.formulaone.ui.navMenuFragments.news

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.R
import com.example.formulaone.Resource
import com.example.formulaone.adapters.NewsAdapter
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.databinding.FragmentFragmentNewsBinding
import com.example.formulaone.databinding.FragmentMainBinding
import com.example.formulaone.ui.mainFragment.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint

class FragmentNews : BaseFragment<FragmentFragmentNewsBinding>(FragmentFragmentNewsBinding::inflate) {

    private val fragmentNewsViewModel: FragmentNewsViewModel by viewModels()
    private val newsAdapter: NewsAdapter by lazy { NewsAdapter() }

    override fun viewCreated() {
        fragmentNewsViewModel.getNews()
        observe1()
        setupRecycler()
    }

    override fun listeners() {

    }

        private fun setupRecycler() {
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }

    private fun observe1() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                fragmentNewsViewModel.newsState.collectLatest {
                    when (it) {
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            newsAdapter.submitList(it.data)
                        }
                    }
                }
            }
        }
    }

}
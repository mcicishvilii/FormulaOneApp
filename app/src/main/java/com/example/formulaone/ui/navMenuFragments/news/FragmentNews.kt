package com.example.formulaone.ui.navMenuFragments.news

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.common.Resource
import com.example.formulaone.ui.adapters.NewsAdapter
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.databinding.FragmentFragmentNewsBinding
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
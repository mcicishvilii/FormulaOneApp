package com.example.formulaone.ui.navMenuFragments.news

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.databinding.FragmentFragmentNewsBinding
import com.example.formulaone.ui.adapters.NewsAdapter
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.common.bases.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint

class FragmentNews :
    BaseFragment<FragmentFragmentNewsBinding>(FragmentFragmentNewsBinding::inflate) {

    private val fragmentNewsViewModel: FragmentNewsViewModel by viewModels()
    private val newsAdapter: NewsAdapter by lazy { NewsAdapter() }

    private var searchJob: Job? = null

    override fun viewCreated() {

        observe1()
        setupRecycler()
    }

//    override fun listeners() {
//        newsAdapter.setOnItemClickListener { article, _ ->
//            val uri: Uri = Uri.parse(article.url) // missing 'http://' will cause crashed
//            val intent = Intent(Intent.ACTION_VIEW, uri)
//            startActivity(intent)
//        }
//    }

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

//    private fun observe1() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                fragmentNewsViewModel.newsState.collectLatest {
//                    when (it) {
//                        is Resource.Error -> {
//
//                        }
//                        is Resource.Loading -> {
//
//                        }
//                        is Resource.Success -> {
//                            newsAdapter.submitList(it.data)
//                        }
//                    }
//                }
//            }
//        }
//    }


    private fun observe1() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            fragmentNewsViewModel.searchPlayers()
                .collectLatest {
                    newsAdapter.submitData(it)
                }
        }
    }

    override fun listeners() {
    }
}
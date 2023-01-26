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

    override fun listeners() {

        share()
        gotoLink()


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
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            fragmentNewsViewModel.searchPlayers()
                .collectLatest {
                    newsAdapter.submitData(it)
                }
        }
    }

    private fun share() {
        newsAdapter.apply {
            setOnShareClickListener { ticket, _ ->
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "${ticket.url}")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }

    private fun gotoLink(){
        newsAdapter.setOnGotoClickListener { article, _ ->
            val uri: Uri = Uri.parse(article.url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

}
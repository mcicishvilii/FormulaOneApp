package com.example.formulaone.ui.mainFragment

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.DriversAdapter
import com.example.formulaone.R
import com.example.formulaone.Resource
import com.example.formulaone.adapters.NewsAdapter
import com.example.formulaone.databinding.FragmentMainBinding
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.ui.navMenuFragments.drivers.list.DriversFragment
import com.example.formulaone.ui.navMenuFragments.schedule.ScheduleFragment
import com.example.formulaone.ui.navMenuFragments.settings.SettingsFragment
import com.example.formulaone.ui.navMenuFragments.teams.TeamsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val mainViewModel: MainViewModel by viewModels()
    private val newsAdapter: NewsAdapter by lazy { NewsAdapter() }



    override fun viewCreated() {

        mainViewModel.getData()
        mainViewModel.getNews()
        observe1()
        observe()


        val bottomNav: BottomNavigationView = binding.navbar

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.firstFragment -> replaceFragment(DriversFragment())
                R.id.secondFragment -> replaceFragment(TeamsFragment())
                R.id.thirdFragment -> replaceFragment(SettingsFragment())
                R.id.fourthFragment -> replaceFragment(ScheduleFragment())
                else -> {}
            }
            true
        }
    }


    override fun listeners() {

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainContainer, fragment)
        fragmentTransaction.commit()
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

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.state.collectLatest {
                    when (it) {
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {
                            Log.d("newsebi",it.loading.toString())
                        }
                        is Resource.Success -> {
                            binding.tv1stDriver.text = it.data.winner
                            binding.lastRaceLocation.text = "The winner of the ${it.data.country}"

                        }
                    }
                }
            }
        }
    }

    private fun observe1() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.newsState.collectLatest {
                    when (it) {
                        is Resource.Error -> {
                            Log.d("newsebi","bebiashenisam ${it.error}")
                        }
                        is Resource.Loading -> {
                            Log.d("newsebi",it.loading.toString())
                        }
                        is Resource.Success -> {
                            setupRecycler()
                            newsAdapter.submitList(it.data)
                            Log.d("newsebi",it.data.toString())
                        }
                    }
                }
            }
        }
    }


}


package com.example.formulaone.ui.mainFragment

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.formulaone.DriversAdapter
import com.example.formulaone.R
import com.example.formulaone.Resource
import com.example.formulaone.adapters.BottomNavViewPagerAdapter
import com.example.formulaone.adapters.NewsAdapter
import com.example.formulaone.adapters.ViewPagerAdapter
import com.example.formulaone.databinding.FragmentMainBinding
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.ui.navMenuFragments.drivers.list.DriversFragment
import com.example.formulaone.ui.navMenuFragments.schedule.ScheduleFragment
import com.example.formulaone.ui.navMenuFragments.settings.SettingsFragment
import com.example.formulaone.ui.navMenuFragments.teams.TeamsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout


    override fun viewCreated() {

        mainViewModel.getData()
        setupTabLayout()
        observe()
    }


    override fun listeners() {

    }

    private fun setupTabLayout() {
        viewPager = binding.viewPager
        tabLayout = binding.tabLayout
        viewPager.adapter = BottomNavViewPagerAdapter(requireActivity())
        TabLayoutMediator(tabLayout,viewPager){tab,index ->
            tab.text = when(index){
                0 -> {"Drivers"}
                1 -> {"Teams"}
                2 -> {"Settings"}
                3 -> {"Schedule"}
                4 -> {"News"}
                else -> {"Tab Not Found"}
            }
        }.attach()
    }





    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.state.collectLatest {
                    when (it) {
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

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




}


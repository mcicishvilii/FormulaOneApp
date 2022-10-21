package com.example.formulaone.ui.mainFragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.example.formulaone.R
import com.example.formulaone.Resource
import com.example.formulaone.adapters.BottomNavViewPagerAdapter
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.databinding.FragmentMainBinding
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
        viewPager.isUserInputEnabled = false
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
        setupTabIcons()
    }

    private fun setupTabIcons() {
        tabLayout.getTabAt(0)?.setIcon(R.drawable.racing_helmet_svgrepo_com)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_baseline_outlined_flag_24)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_baseline_settings_24)
        tabLayout.getTabAt(3)?.setIcon(R.drawable.ic_baseline_calendar_today_24)
        tabLayout.getTabAt(4)?.setIcon(R.drawable.albon)

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


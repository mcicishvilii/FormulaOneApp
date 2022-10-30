package com.example.formulaone.ui.mainFragment

import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.example.formulaone.R
import com.example.formulaone.Resource
import com.example.formulaone.adapters.BottomNavViewPagerAdapter
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.data.remote.raceSchedule.Circuit
import com.example.formulaone.data.remote.raceSchedule.Location
import com.example.formulaone.databinding.FragmentMainBinding
import com.example.formulaone.domain.model.remote.RaceScheduleDomain
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val mainViewModel: MainViewModel by viewModels()

    val races = mutableListOf<RaceScheduleDomain>()

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun viewCreated() {
        mainViewModel.getSchedule()
        setupTabLayout()
        observe()
//        timer.start()
    }

    override fun listeners() {

    }

    private fun setupTabLayout() {
        viewPager = binding.viewPager
        tabLayout = binding.tabLayout
        viewPager.isUserInputEnabled = false
        viewPager.adapter = BottomNavViewPagerAdapter(requireActivity())

        TabLayoutMediator(tabLayout, viewPager) { tab, index ->
            tab.text = when (index) {
                0 -> "Drivers"
                1 -> "Teams"
                2 -> "Settings"
                3 -> "Schedule"
                4 -> "News"
                else -> "Tab Not Found"
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

                        }
                    }
                }
            }
        }
    }


//    val timer = object : CountDownTimer(  100000000, 1000) {
//        override fun onTick(millisUntilFinished: Long) {
//            binding.tv1stDriver.text = (millisUntilFinished / 1000).toString()
//        }
//
//        override fun onFinish() {
//            binding.tv1stDriver.text = "Timer"
//
//        }
//    }
}






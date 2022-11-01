package com.example.formulaone.ui.navMenuFragments.schedule

import androidx.viewpager2.widget.ViewPager2
import com.example.formulaone.ui.adapters.ViewPagerAdapter
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.databinding.FragmentScheduleBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ScheduleFragment : BaseFragment<FragmentScheduleBinding>(FragmentScheduleBinding::inflate) {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    override fun viewCreated() {
        setupTabLayout()
    }

    override fun listeners() {

    }


    private fun setupTabLayout() {
        viewPager = binding.viewPager2
        tabLayout = binding.tabLayout
        viewPager.adapter = ViewPagerAdapter(requireActivity())
        TabLayoutMediator(tabLayout,viewPager){tab,index ->
            tab.text = when(index){
                0 -> {"Recent Races"}
                1 -> {"Upcoming Races"}
                else -> {"Tab Not Found"}
            }
        }.attach()
    }


}
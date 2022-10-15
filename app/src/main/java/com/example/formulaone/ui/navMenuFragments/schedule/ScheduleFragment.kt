package com.example.formulaone.ui.navMenuFragments.schedule

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.formulaone.DriversAdapter
import com.example.formulaone.R
import com.example.formulaone.adapters.ViewPagerAdapter
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.data.Recyclers
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
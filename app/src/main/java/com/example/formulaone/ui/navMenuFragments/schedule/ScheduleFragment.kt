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
import com.example.formulaone.R
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.databinding.FragmentScheduleBinding
import com.google.android.material.tabs.TabLayout

class ScheduleFragment : BaseFragment<FragmentScheduleBinding>(FragmentScheduleBinding::inflate) {
    override fun viewCreated() {
        setupTabLayout()
    }

    override fun listeners() {

    }


    private fun setupTabLayout(){

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab?) {
                findNavController().navigate(ScheduleFragmentDirections.actionScheduleFragmentToRecentRacesFragment())
//                Toast.makeText(requireContext(),"misho",Toast.LENGTH_SHORT).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                Toast.makeText(requireContext(),"unselect",Toast.LENGTH_SHORT).show()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
//                Toast.makeText(requireContext(),"reselect",Toast.LENGTH_SHORT).show()
            }
        })
    }




}
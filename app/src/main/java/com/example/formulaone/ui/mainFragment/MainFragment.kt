package com.example.formulaone.ui.mainFragment

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.formulaone.DriversAdapter
//import com.example.formulaone.DriversAdapter
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentMainBinding
import com.example.formulaone.ui.BaseFragment
import com.example.formulaone.ui.navMenuFragments.drivers.DriversFragment
import com.example.formulaone.ui.navMenuFragments.settings.SettingsFragment
import com.example.formulaone.ui.navMenuFragments.teams.TeamsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.log

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val mainViewModel: MainViewModel by viewModels()
//    private val driversAdapter: DriversAdapter by lazy { DriversAdapter() }


    override fun viewCreated() {

//        setupRecycler()

        mainViewModel.getCurrentStandingsLivedata()
        mainViewModel.getCurrentStandingsLivedata().observe(viewLifecycleOwner) {
            binding.tv1stDriver.text = buildString {
                append(it?.get(0)?.code ?: "")
                append(" ")
                append(it?.get(0)?.givenName)
                append(" ")
                append(it?.get(0)?.familyName)
            }
        }


        val bottomNav: BottomNavigationView = binding.navbar

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.firstFragment -> replaceFragment(DriversFragment())
                R.id.secondFragment -> replaceFragment(TeamsFragment())
                R.id.thirdFragment -> replaceFragment(SettingsFragment())
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

//    private fun setupRecycler() {
//        binding.rvDrivers.apply {
//            adapter = driversAdapter
//            layoutManager =
//                LinearLayoutManager(requireContext(),
//                    LinearLayoutManager.VERTICAL,
//                    false)
//        }
//    }


}
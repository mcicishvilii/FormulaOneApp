package com.example.formulaone.ui.mainFragment

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.formulaone.R
import com.example.formulaone.Resource
import com.example.formulaone.databinding.FragmentMainBinding
import com.example.formulaone.ui.BaseFragment
import com.example.formulaone.ui.navMenuFragments.drivers.DriversFragment
import com.example.formulaone.ui.navMenuFragments.settings.SettingsFragment
import com.example.formulaone.ui.navMenuFragments.teams.TeamsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val mainViewModel: MainViewModel by viewModels()


    override fun viewCreated() {
        observe()


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
                            binding.tv1stDriver.text = buildString {
                                append(it.data.MRData.StandingsTable.StandingsLists[0].DriverStandings[0].Driver.givenName)
                                append(" ")
                            } +
                                    "${it.data.MRData.StandingsTable.StandingsLists[0].DriverStandings[0].Driver.familyName} " +
                                    "${it.data.MRData.StandingsTable.StandingsLists[0].DriverStandings[0].Driver.code}"
                        }
                    }
                }
            }
        }
    }

}


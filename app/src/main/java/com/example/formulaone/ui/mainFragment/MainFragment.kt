package com.example.formulaone.ui.mainFragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentMainBinding
import com.example.formulaone.ui.BaseFragment
import com.example.formulaone.ui.navMenuFragments.drivers.DriversFragment
import com.example.formulaone.ui.navMenuFragments.settings.SettingsFragment
import com.example.formulaone.ui.navMenuFragments.teams.TeamsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val mainViewModel: MainViewModel by viewModels()


    override fun viewCreated() {

        val bottomNav: BottomNavigationView = binding.navbar

        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
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

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainContainer,fragment)
        fragmentTransaction.commit()
    }


}
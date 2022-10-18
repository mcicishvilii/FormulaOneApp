package com.example.formulaone.adapters

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.formulaone.ui.navMenuFragments.news.FragmentNews
import com.example.formulaone.ui.navMenuFragments.drivers.list.DriversFragment
import com.example.formulaone.ui.navMenuFragments.schedule.ScheduleFragment
import com.example.formulaone.ui.navMenuFragments.settings.SettingsFragment
import com.example.formulaone.ui.navMenuFragments.teams.TeamsFragment

class BottomNavViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount() = 5

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {DriversFragment()}
            1 -> {TeamsFragment()}
            2 -> {SettingsFragment()}
            3 -> {ScheduleFragment()}
            4 -> {FragmentNews()}

            else ->{throw Resources.NotFoundException("not found")}
        }
    }


}

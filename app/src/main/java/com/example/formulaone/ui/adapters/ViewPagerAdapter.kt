package com.example.formulaone.ui.adapters

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.formulaone.ui.navMenuFragments.schedule.recent.RecentRacesFragment
import com.example.formulaone.ui.navMenuFragments.schedule.upcoming.UpcomingRacesFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {RecentRacesFragment()}
            1 -> {UpcomingRacesFragment()}

            else ->{throw Resources.NotFoundException("not found")}
        }
    }


}

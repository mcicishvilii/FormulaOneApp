package com.example.formulaone.adapters.SchedulesAdapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.formulaone.DriversAdapter
import com.example.formulaone.R
import com.example.formulaone.data.Recyclers
import com.example.formulaone.data.remote.drivers.drivers_standings.DriverStandingsDto
import com.example.formulaone.databinding.SingleDriverLayoutBinding
import com.example.formulaone.ui.navMenuFragments.drivers.list.DriversFragment
import com.example.formulaone.ui.navMenuFragments.schedule.ScheduleFragment
import com.example.formulaone.ui.navMenuFragments.schedule.recent.RecentRacesFragment
import com.example.formulaone.ui.navMenuFragments.schedule.upcoming.UpcomingRacesFragment
import com.example.formulaone.ui.navMenuFragments.settings.SettingsFragment
import com.example.formulaone.ui.navMenuFragments.teams.TeamsFragment

class BottomNavViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {DriversFragment()}
            1 -> {TeamsFragment()}
            2 -> {SettingsFragment()}
            3 -> {ScheduleFragment()}

            else ->{throw Resources.NotFoundException("not found")}
        }
    }


}

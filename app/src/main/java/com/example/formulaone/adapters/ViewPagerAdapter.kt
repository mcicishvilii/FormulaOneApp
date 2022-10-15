package com.example.formulaone.adapters

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
import com.example.formulaone.databinding.SingleRecyclerViewBinding
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

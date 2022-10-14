package com.example.formulaone.ui.navMenuFragments.schedule.upcoming

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.formulaone.R

class UpcomingRacesFragment : Fragment() {

    companion object {
        fun newInstance() = UpcomingRacesFragment()
    }

    private lateinit var viewModel: UpcomingRacesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_upcoming_races, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpcomingRacesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
package com.example.formulaone.ui.navMenuFragments.schedule.recent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.formulaone.R

class RecentRacesFragment : Fragment() {

    companion object {
        fun newInstance() = RecentRacesFragment()
    }

    private lateinit var viewModel: RecentRacesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recent_races, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecentRacesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
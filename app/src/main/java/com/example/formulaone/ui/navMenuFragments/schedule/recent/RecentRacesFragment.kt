package com.example.formulaone.ui.navMenuFragments.schedule.recent

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.formulaone.ui.navMenuFragments.schedule.upcoming.UpcomingRacesViewModel
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.Resource
import com.example.formulaone.adapters.ConstructorsAdapter
import com.example.formulaone.adapters.RecentRacesAdapter
import com.example.formulaone.adapters.UpcomingRaceAdapter
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.databinding.FragmentRecentRacesBinding
import com.example.formulaone.databinding.FragmentUpcomingRacesBinding
import com.example.formulaone.ui.navMenuFragments.teams.TeamsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class RecentRacesFragment : BaseFragment<FragmentRecentRacesBinding>(FragmentRecentRacesBinding::inflate) {
    private val myAdapter: RecentRacesAdapter by lazy { RecentRacesAdapter() }
    private val recentRacesViewModel: RecentRacesViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun viewCreated() {
        observe()
    }

    override fun listeners() {

    }

    private fun setupRecycler() {
        binding.rvRecentRaces.apply {
            adapter = myAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun observe() {
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                recentRacesViewModel.state.collectLatest {
                    when (it) {
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            val filteredList = it.data.filter {
                                val time = Calendar.getInstance().time
                                val formatterCurrentTime = SimpleDateFormat("yyyy-MM-dd")
                                val formatterNow = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                                val currentTime = formatterCurrentTime.format(time)
                                val dateNow = LocalDate.parse(currentTime, formatterNow)

                                val dateFromModel =it.date
                                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                                val date = LocalDate.parse(dateFromModel, formatter)

                                dateNow > date
                            }
                            myAdapter.submitList(filteredList)
                        }
                    }
                }
            }
        }
    }

}
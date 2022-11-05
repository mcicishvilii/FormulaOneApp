package com.example.formulaone.ui.mainFragment

import android.os.Build
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.example.formulaone.R
import com.example.formulaone.common.MyFirebaseMessagingService
import com.example.formulaone.common.Resource
import com.example.formulaone.ui.adapters.BottomNavViewPagerAdapter
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.common.utils.TimeFormater
import com.example.formulaone.common.utils.TimeFormaterIMPL
import com.example.formulaone.databinding.FragmentMainBinding
import com.example.formulaone.domain.model.RaceScheduleDomain
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val mainViewModel: MainViewModel by viewModels()
    val service by lazy { context?.let { MyFirebaseMessagingService(it.applicationContext) } }


    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun viewCreated() {
        mainViewModel.getSchedule()
        setupTabLayout()
        observe()
    }

    override fun listeners() {

    }




    private fun setupTabLayout() {
        viewPager = binding.viewPager
        tabLayout = binding.tabLayout
        viewPager.isUserInputEnabled = false
        viewPager.adapter = BottomNavViewPagerAdapter(requireActivity())

        TabLayoutMediator(tabLayout, viewPager) { tab, index ->
            tab.text = when (index) {
                0 -> "Drivers"
                1 -> "Teams"
                2 -> "Settings"
                3 -> "Schedule"
                4 -> "News"
                else -> "Tab Not Found"
            }
        }.attach()
        setupTabIcons()
    }

    private fun setupTabIcons() {
        tabLayout.getTabAt(0)?.setIcon(R.drawable.racing_helmet_svgrepo_com)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_baseline_outlined_flag_24)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_baseline_settings_24)
        tabLayout.getTabAt(3)?.setIcon(R.drawable.ic_baseline_calendar_today_24)
        tabLayout.getTabAt(4)?.setIcon(R.drawable.albon)
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.state.collect() {
                    when (it) {
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            binding.tv1stDriver.text = "${it.data[0].Circuit.circuitName}"
                            binding.dateContainer.text = it.data[0].date
                            binding.tvLocation.text = it.data[0].Circuit.Location.locality


                            val raceDay = "${it.data[0].Circuit.Location.country} on ${it.data[0].date} at ${it.data[0].time.dropLast(4)}"



                            val lat = it.data[0].Circuit.Location.lat.toDouble()
                            val long = it.data[0].Circuit.Location.long.toDouble()

                            mainViewModel.getWeather(lat, long)

                            val dateNow = TimeFormaterIMPL().formatCurrentTime()

                            val dateFromModel = it.data[0].date
                            val dateMogonili = "2022-11-07"
                            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            val date = LocalDate.parse(dateMogonili, formatter)

                            if (dateNow in date.minusDays(1)..date){
                                observeWeather()
                                service?.showNotification(requireContext(),raceDay).toString()
                                binding.apply {
                                    lastRaceContainer.visibility = View.VISIBLE
                                    lastRaceLocation.visibility = View.VISIBLE
                                    tv1stDriver.visibility = View.VISIBLE
                                    tvWeather.visibility = View.VISIBLE
                                    ivWeatherIcon.visibility = View.VISIBLE
                                    tvLocation.visibility = View.VISIBLE
                                    dateContainer.visibility = View.VISIBLE
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private fun observeWeather() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.weatherState.collect() {

                    when(it){
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            binding.tvWeather.text = "${it.data.daily.temperature2mMax[1]} C\u00B0"
                            weatherIcon(it.data.daily.weatherCode[1])
                        }
                    }
                }
            }
        }
    }

    fun weatherIcon(data:Int){
        if ( data in 0..3 ){
            binding.ivWeatherIcon.setImageResource(R.drawable.sun_svgrepo_com)
        }
        else if(data in 51..67){
            binding.ivWeatherIcon.setImageResource(R.drawable.rain_svgrepo_com)
        }
        else if(data in 95..99){
            binding.ivWeatherIcon.setImageResource(R.drawable.thunder_svgrepo_com)
        }
        else{
            binding.ivWeatherIcon.setImageResource(R.drawable.cloudy_svgrepo_com)
        }
    }
}






package com.example.formulaone.ui.mainFragment

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentMainBinding
import com.example.formulaone.ui.adapters.BottomNavViewPagerAdapter
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.common.bases.BaseFragment
import com.example.formulaoneapplicationn.common.utils.TimeFormaterIMPL
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val mainViewModel: MainViewModel by viewModels()


    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun viewCreated() {

        mainViewModel.getRacing()
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
                2 -> "More"
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
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_baseline_more_horiz_24)
        tabLayout.getTabAt(3)?.setIcon(R.drawable.ic_baseline_calendar_today_24)
        tabLayout.getTabAt(4)?.setIcon(R.drawable.albon)
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.state.collect() {time ->
                    when (time) {
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {

                            val timeFromData = time.data[0].time
                            val dateFromData = time.data[0].date

                            val combined = dateFromData + "T" + timeFromData

                            val ans = convertISOTimeToDate(combined)

                            Log.d("ciciko","$combined amas vawyvdi punqcias")


                            binding.dateContainer.text = time.data[0].date + " : " + ans.toString()
                            binding.tv1stDriver.text = time.data[0].circuit.circuitName
                            binding.tvLocation.text = time.data[0].circuit.location.locality

                            val lat = time.data[0].circuit.location.lat.toDouble()
                            val long = time.data[0].circuit.location.long.toDouble()

                            mainViewModel.getWeather(lat, long)

                            val dateNow = TimeFormaterIMPL().formatCurrentTime()

                            val dateFromModel = time.data.last().date

                            val dateMogonili = "2022-11-22"

                            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            val date = LocalDate.parse(dateMogonili, formatter)



                            if (dateNow in date.minusDays(1)..date){
                                observeWeather()
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
                            binding.tvWeather.text = "${it.data.temperature2mMax[0]} C\u00B0"
                            weatherIcon(it.data.weatherCode[0])
                        }
                    }
                }
            }
        }
    }

    private fun convertISOTimeToDate(isoTime: String): String? {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
        var convertedDate: Date? = null
        var formattedDate: String? = null
        try {
            convertedDate = sdf.parse(isoTime)


            val misho = convertedDate.time
            val changed = misho.toString().dropLast(3)
            val backtoMisho = changed.toLong()

            val tb = Instant.ofEpochSecond(backtoMisho)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
            Log.d("ciciko","$tb amas mibrunebs punqcia" )

            formattedDate = tb.plusHours(4).toString().drop(11)

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formattedDate
    }

    fun weatherIcon(data:Int){
        if ( data in 0..3 ){
            binding.ivWeatherIcon.setImageResource(R.drawable.sun)
        }
        else if(data in 51..67){
            binding.ivWeatherIcon.setImageResource(R.drawable.rain)
        }
        else if(data in 95..99){
            binding.ivWeatherIcon.setImageResource(R.drawable.thunder)
        }
        else{
            binding.ivWeatherIcon.setImageResource(R.drawable.clouds)
        }
    }


}





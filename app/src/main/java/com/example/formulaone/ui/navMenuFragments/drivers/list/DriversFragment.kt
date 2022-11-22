package com.example.formulaone.ui.navMenuFragments.drivers.list

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.DriversAdapter
import com.example.formulaone.data.model.drivers.quali.Driver
import com.example.formulaone.databinding.FragmentDriversBinding
import com.example.formulaone.ui.navMenuFragments.drivers.DriversDetails
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.common.bases.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class DriversFragment : BaseFragment<FragmentDriversBinding>(FragmentDriversBinding::inflate) {
    private val driversViewModel: DriversViewModel by viewModels()
    private val driversAdapter: DriversAdapter by lazy { DriversAdapter() }

//    val ma
    val listDriversQuali = mutableListOf<String>()
    var answer = ""

    override fun viewCreated() {
        driversViewModel.getDrivers()
        driversViewModel.getQuali()
        observe()
        observeQuali()

    }

    override fun listeners() {
        toDetails()
    }

    private fun toDetails(){
        var qualis = ""

        driversAdapter.apply {
            setOnItemClickListener{ driver,_ ->
                val dropppedAnswer = answer.dropLast(3)
//                Log.d("answe",dropppedAnswer)
//                Log.d("answe",driver.Driver.driverId)
                if(driver.Driver.driverId == dropppedAnswer){
                    qualis = answer.last().toString()
                }else{
                    qualis = "0"
                }

                findNavController().navigate(DriversFragmentDirections.actionDriversFragmentToDriverDetailsFragment(
                    DriversDetails(
                        driver.Driver.givenName,
                        driver.Driver.familyName,
                        driver.wins,
                        driver.position,
                        driver.Driver.nationality,
                        driver.Driver.dateOfBirth,
                        driver.Driver.permanentNumber,
                        driver.Constructors[0].name,
                        qualis = qualis
                    )
                ))
            }
        }
    }

    private fun setupRecycler() {
        binding.rvDrivers.apply {
            adapter = driversAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }

    private fun observe(){
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                driversViewModel.state.collectLatest {
                    when(it){
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {
                            binding.tvNowLoading.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            driversAdapter.submitList(it.data)
                            binding.tvNowLoading.visibility = View.INVISIBLE
                        }
                    }
                }
            }
        }
    }



    private fun observeQuali(){
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                driversViewModel.qualiState.collectLatest {
                    when(it){
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            it.data.forEach {
                                it.qualifyingResults.forEach{
//                                    Log.d("qualif",it.position)
                                    listDriversQuali.add(it.driver.driverId)
                                }
                            }
                            for (item in listDriversQuali.distinct()) {
                                val coll = Collections.frequency(listDriversQuali, item)

                                Log.d("answe", answer.length.toString())
                            }
                        }
                    }
                }
            }
        }
    }
}
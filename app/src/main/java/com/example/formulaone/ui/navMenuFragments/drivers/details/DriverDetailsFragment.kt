package com.example.formulaone.ui.navMenuFragments.drivers.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.formulaone.R
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.databinding.FragmentDriverDetailsBinding
import com.example.formulaone.ui.navMenuFragments.drivers.list.DriversFragment
import com.example.formulaone.ui.navMenuFragments.drivers.list.DriversFragmentDirections


class DriverDetailsFragment : BaseFragment<FragmentDriverDetailsBinding>(FragmentDriverDetailsBinding::inflate){
    val args: DriverDetailsFragmentArgs by navArgs()
    override fun viewCreated() {
        val name = args.details
        binding.tvDriverFirstName.text = name?.name
        binding.tvDriverLastName.text = name?.lastName

//        val onBackPressedCallback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                findNavController().navigate(DriverDetailsFragmentDirections.actionDriverDetailsFragmentToDriversFragment())
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

    }

    override fun listeners() {

    }


}
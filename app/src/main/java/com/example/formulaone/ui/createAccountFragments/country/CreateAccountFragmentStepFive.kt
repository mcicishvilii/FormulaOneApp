package com.example.formulaone.ui.createAccountFragments.country

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentCreateAccountFragmentStepFiveBinding
import com.example.formulaone.ui.BaseFragment

class CreateAccountFragmentStepFive : BaseFragment<FragmentCreateAccountFragmentStepFiveBinding>(
    FragmentCreateAccountFragmentStepFiveBinding::inflate) {

    override fun viewCreated() {


    }

    override fun listeners() {
        spinner()
    }


    private fun spinner(){
        val countries = resources.getStringArray(R.array.countries)


        val adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_item, countries)
        binding.spCountryOfResidence.adapter = adapter


        binding.spCountryOfResidence.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long,
            ) {
                if(position == 0){
                    binding.btnNext.visibility = View.INVISIBLE
                }else{
                    binding.btnNext.visibility = View.VISIBLE

                    //string splitting
                    val selectedCountry = getString(R.string.selected_item) + " " + "" + countries[position]
                    Log.d("tag","first $selectedCountry")
                    val splittedString = selectedCountry.split(":")
                    val splitedCountry = splittedString[1]
                    Log.d("tag","splited $splitedCountry")
                    // navigation with args
                    binding.btnNext.setOnClickListener {
                        findNavController().navigate(CreateAccountFragmentStepFiveDirections.actionCreateAccountFragmentStepFiveToCreateAccountFragmentStepSix(splitedCountry))
                    }
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

}
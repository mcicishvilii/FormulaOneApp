package com.example.formulaone.ui.createAccountFragments.lastName

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentCreateAccountFragmentStepTwoBinding
import com.example.formulaone.databinding.FragmentMainBinding
import com.example.formulaone.ui.BaseFragment

class CreateAccountFragmentStepTwo : BaseFragment<FragmentCreateAccountFragmentStepTwoBinding>(FragmentCreateAccountFragmentStepTwoBinding::inflate) {

    override fun viewCreated() {


        binding.etLastName.doOnTextChanged { text, start, before, count ->
            binding.btnNext.visibility = View.VISIBLE
        }

    }

    override fun listeners() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_createAccountFragmentStepTwo_to_createAccountFragmentStepThree)
        }
    }

}
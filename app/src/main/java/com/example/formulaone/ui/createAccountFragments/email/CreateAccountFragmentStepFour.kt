package com.example.formulaone.ui.createAccountFragments.email

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentCreateAccountFragmentStepFourBinding
import com.example.formulaone.databinding.FragmentMainBinding
import com.example.formulaone.ui.BaseFragment

class CreateAccountFragmentStepFour : BaseFragment<FragmentCreateAccountFragmentStepFourBinding>(FragmentCreateAccountFragmentStepFourBinding::inflate) {

    override fun viewCreated() {
        binding.etEmail.doOnTextChanged { text, start, before, count ->
            binding.btnNext.visibility = View.VISIBLE
        }
    }

    override fun listeners() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_createAccountFragmentStepFour_to_createAccountFragmentStepFive)
        }
    }

}
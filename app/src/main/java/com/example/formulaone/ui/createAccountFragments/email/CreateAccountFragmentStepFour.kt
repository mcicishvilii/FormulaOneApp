package com.example.formulaone.ui.createAccountFragments.email

import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.formulaone.databinding.FragmentCreateAccountFragmentStepFourBinding
import com.example.formulaone.ui.BaseFragment

class CreateAccountFragmentStepFour : BaseFragment<FragmentCreateAccountFragmentStepFourBinding>(FragmentCreateAccountFragmentStepFourBinding::inflate) {

    override fun viewCreated() {
        binding.etEmail.doOnTextChanged { text, start, before, count ->
            binding.btnNext.visibility = View.VISIBLE
        }
    }

    override fun listeners() {
        val mail = binding.etEmail.text.toString()

        binding.btnNext.setOnClickListener {
            findNavController().navigate(CreateAccountFragmentStepFourDirections
                .actionCreateAccountFragmentStepFourToCreateAccountFragmentStepFive())
        }
    }

}
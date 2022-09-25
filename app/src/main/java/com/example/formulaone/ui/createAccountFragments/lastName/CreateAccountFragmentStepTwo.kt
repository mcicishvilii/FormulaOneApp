package com.example.formulaone.ui.createAccountFragments.lastName

import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentCreateAccountFragmentStepTwoBinding
import com.example.formulaone.common.bases.BaseFragment

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
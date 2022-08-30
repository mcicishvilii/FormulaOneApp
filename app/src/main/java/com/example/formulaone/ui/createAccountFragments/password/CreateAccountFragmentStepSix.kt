package com.example.formulaone.ui.createAccountFragments.password

import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentCreateAccountFragmentStepSixBinding
import com.example.formulaone.ui.BaseFragment

class CreateAccountFragmentStepSix : BaseFragment<FragmentCreateAccountFragmentStepSixBinding>(FragmentCreateAccountFragmentStepSixBinding::inflate) {

    val args: CreateAccountFragmentStepSixArgs by navArgs()
    override fun viewCreated() {
        binding.tvWelcome.text = args.countryName

        binding.etPassword.doOnTextChanged { text, start, before, count ->
            binding.btnNext.visibility = View.VISIBLE
        }
    }

    override fun listeners() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_createAccountFragmentStepSix_to_mainFragment)
        }
    }

}

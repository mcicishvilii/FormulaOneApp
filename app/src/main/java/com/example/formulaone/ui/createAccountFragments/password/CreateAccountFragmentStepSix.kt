package com.example.formulaone.ui.createAccountFragments.password

import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.formulaone.databinding.FragmentCreateAccountFragmentStepSixBinding
import com.example.formulaone.common.bases.BaseFragment
import com.google.firebase.auth.FirebaseAuth

class CreateAccountFragmentStepSix : BaseFragment<FragmentCreateAccountFragmentStepSixBinding>(FragmentCreateAccountFragmentStepSixBinding::inflate) {

    private lateinit var auth: FirebaseAuth
    val args: CreateAccountFragmentStepSixArgs by navArgs()


    override fun viewCreated() {
        auth = FirebaseAuth.getInstance()


        binding.tvWelcome.text = args.countryName
        binding.etPassword.doOnTextChanged { text, start, before, count ->
            binding.btnNext.visibility = View.VISIBLE
        }
    }

    override fun listeners() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(CreateAccountFragmentStepSixDirections.actionCreateAccountFragmentStepSixToMainFragment())
        }
    }




}

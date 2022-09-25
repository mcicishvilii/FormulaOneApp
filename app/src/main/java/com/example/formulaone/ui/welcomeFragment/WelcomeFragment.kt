package com.example.formulaone.ui.welcomeFragment

import androidx.navigation.fragment.findNavController
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentWelcomeBinding
import com.example.formulaone.common.bases.BaseFragment

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {
    override fun viewCreated() {

    }

    override fun listeners() {
        binding.btnCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_createAccountFragment)
        }

        binding.tvSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_signInFragment)
        }

        binding.tvNotNow.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_mainFragment)
        }


    }


}
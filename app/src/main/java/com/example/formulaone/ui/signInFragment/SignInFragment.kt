package com.example.formulaone.ui.signInFragment

import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.formulaone.databinding.FragmentSignInBinding
import com.example.formulaone.common.bases.BaseFragment

class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {
    override fun viewCreated() {
        binding.etEmail.doOnTextChanged { text, start, before, count ->
            binding.btnSignIn.visibility = View.VISIBLE
        }
    }

    override fun listeners() {
        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignInFragmentStepTwo())
        }
    }


}
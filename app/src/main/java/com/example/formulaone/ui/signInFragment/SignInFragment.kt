package com.example.formulaone.ui.signInFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentSignInBinding
import com.example.formulaone.ui.BaseFragment

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
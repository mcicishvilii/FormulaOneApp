package com.example.formulaone.ui.createAccountFragments.firstName

import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.formulaone.databinding.FragmentCreateAccountBinding
import com.example.formulaone.common.bases.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class CreateAccountFragment : BaseFragment<FragmentCreateAccountBinding>(FragmentCreateAccountBinding::inflate) {


    private lateinit var auth: FirebaseAuth


    override fun viewCreated() {
        checkVisibility()
        auth = Firebase.auth

    }

    override fun listeners() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(CreateAccountFragmentDirections.actionCreateAccountFragmentToMainFragment())
        }
    }


    private fun checkVisibility(){
        binding.etFirstNameImpl.doOnTextChanged { text, start, before, count ->
            binding.btnNext.visibility = View.VISIBLE
        }
    }


}
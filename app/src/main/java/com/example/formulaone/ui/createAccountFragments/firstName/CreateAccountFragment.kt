package com.example.formulaone.ui.createAccountFragments.firstName

import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentCreateAccountBinding
import com.example.formulaone.databinding.FragmentMainBinding
import com.example.formulaone.ui.BaseFragment


class CreateAccountFragment : BaseFragment<FragmentCreateAccountBinding>(FragmentCreateAccountBinding::inflate) {

    override fun viewCreated() {
        checkVisibility()

    }

    override fun listeners() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(CreateAccountFragmentDirections.actionCreateAccountFragmentToCreateAccountFragmentStepTwo())
        }
    }


    private fun checkVisibility(){
        binding.etFirstName.doOnTextChanged { text, start, before, count ->
            binding.btnNext.visibility = View.VISIBLE
        }
    }




}
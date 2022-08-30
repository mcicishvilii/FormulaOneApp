package com.example.formulaone.ui.createAccountFragments.dob

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentCreateAccountFragmentStepThreeBinding
import com.example.formulaone.databinding.FragmentCreateAccountFragmentStepTwoBinding
import com.example.formulaone.databinding.FragmentMainBinding
import com.example.formulaone.ui.BaseFragment
import java.util.*

class CreateAccountFragmentStepThree : BaseFragment<FragmentCreateAccountFragmentStepThreeBinding>(FragmentCreateAccountFragmentStepThreeBinding::inflate) {

    override fun viewCreated() {

        val today = Calendar.getInstance()
        binding.dpDatePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { _, _, _, _ ->
            binding.btnNext.visibility = View.VISIBLE
        }

    }

    override fun listeners() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_createAccountFragmentStepThree_to_createAccountFragmentStepFour)
        }
    }

}
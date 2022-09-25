package com.example.formulaone.ui.createAccountFragments.dob

import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentCreateAccountFragmentStepThreeBinding
import com.example.formulaone.common.bases.BaseFragment
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
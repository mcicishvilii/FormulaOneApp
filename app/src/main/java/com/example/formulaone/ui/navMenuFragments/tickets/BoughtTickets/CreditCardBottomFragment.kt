package com.example.formulaone.ui.navMenuFragments.tickets.BoughtTickets

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.formulaone.R
import com.example.formulaone.data.local.models.TicketsEntity
import com.example.formulaone.databinding.CreditCardLayoutOldBinding
import com.example.formulaone.ui.navMenuFragments.tickets.FragmentTicketsArgs
import com.example.formulaone.ui.navMenuFragments.tickets.FragmentTicketsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch

class CreditCardBottomFragment : BottomSheetDialogFragment() {


    private var _binding: CreditCardLayoutOldBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CreditCardLayoutOldBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ckeckIfMasterCardOrVisa()
        binding.apply {

            gobutton.setOnClickListener {
                if (etCardNumber.text.isEmpty() && etCvv.text.isEmpty() && etExpireDate.text.isEmpty()) {
                    Toast.makeText(requireContext(), "fill all the fields", Toast.LENGTH_SHORT)
                        .show()
                } else {

                    dismiss()
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun ckeckIfMasterCardOrVisa() {
        binding.etCardNumber.doOnTextChanged { text, _, _, _ ->
            when {
                text!!.isEmpty() -> {
                    Toast.makeText(requireContext(), "please enter card number", Toast.LENGTH_SHORT).show()
                    binding.ivCardType.visibility = View.GONE
                }
                text.first() == '4' -> {
                    binding.ivCardType.setImageResource(R.drawable.visa_seeklogo_com)
                    binding.ivCardType.visibility = View.VISIBLE
                }
                text.first() == '5' -> {
                    binding.ivCardType.setImageResource(R.drawable.mastercard_logo_wine)
                    binding.ivCardType.visibility = View.VISIBLE
                }
            }
        }
    }



    companion object {
        const val TAG = "ModalBottomSheet"
    }
}
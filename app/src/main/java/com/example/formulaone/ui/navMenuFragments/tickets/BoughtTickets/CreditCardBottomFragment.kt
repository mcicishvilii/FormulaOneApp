package com.example.formulaone.ui.navMenuFragments.tickets.BoughtTickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.formulaone.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CreditCardBottomFragment  : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.credit_card_layout, container, false)

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}
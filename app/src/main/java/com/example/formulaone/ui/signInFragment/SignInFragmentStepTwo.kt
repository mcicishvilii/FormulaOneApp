package com.example.formulaone.ui.signInFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.formulaone.R

class SignInFragmentStepTwo : Fragment() {

    companion object {
        fun newInstance() = SignInFragmentStepTwo()
    }

    private lateinit var viewModel: SignInFragmentStepTwoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in_fragment_step_two, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignInFragmentStepTwoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
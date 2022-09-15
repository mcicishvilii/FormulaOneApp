package com.example.formulaone.ui.signInFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentSignInFragmentStepTwoBinding
import com.example.formulaone.ui.BaseFragment
import com.example.formulaone.ui.createAccountFragments.password.CreateAccountFragmentStepSixDirections
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SignInFragmentStepTwo : BaseFragment<FragmentSignInFragmentStepTwoBinding>(FragmentSignInFragmentStepTwoBinding::inflate) {

    private lateinit var auth: FirebaseAuth

    override fun viewCreated() {
        auth = FirebaseAuth.getInstance()



        binding.etEmail.doOnTextChanged { text, start, before, count ->
            binding.btnSignIn.visibility = View.VISIBLE
        }
    }

    override fun listeners() {
        binding.btnSignIn.setOnClickListener {
            loginWithUser()
        }
    }

    private fun loginWithUser(){
        val email = "mcicishvilii@gmail.com"
        val password = "123456"

        if(email.isNotEmpty() && password.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(email,password).await()
                    withContext(Dispatchers.Main){
                        checkLoggedInState()
                        findNavController().navigate(SignInFragmentStepTwoDirections.actionSignInFragmentStepTwoToMainFragment())
                    }
                }catch (e:Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(requireContext(),"wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun checkLoggedInState() {
        val user = auth.currentUser
        if (user == null) {

        } else {

        }
    }



}
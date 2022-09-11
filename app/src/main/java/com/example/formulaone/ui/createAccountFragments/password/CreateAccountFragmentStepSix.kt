package com.example.formulaone.ui.createAccountFragments.password

import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentCreateAccountFragmentStepSixBinding
import com.example.formulaone.ui.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class CreateAccountFragmentStepSix : BaseFragment<FragmentCreateAccountFragmentStepSixBinding>(FragmentCreateAccountFragmentStepSixBinding::inflate) {

    private lateinit var auth: FirebaseAuth
    val args: CreateAccountFragmentStepSixArgs by navArgs()


    override fun viewCreated() {
        auth = FirebaseAuth.getInstance()

        loginWithUser()

        binding.tvWelcome.text = args.countryName
        binding.etPassword.doOnTextChanged { text, start, before, count ->
            binding.btnNext.visibility = View.VISIBLE
        }
    }

    override fun listeners() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_createAccountFragmentStepSix_to_mainFragment)
        }
    }


    private fun loginWithUser(){
        val email = "mcicishvilii@gmail.com"
        val password = "12345678"

        if(email.isNotEmpty() && password.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(email,password).await()
                    withContext(Dispatchers.Main){
                        checkLoggedInState()
                        findNavController().navigate(CreateAccountFragmentStepSixDirections.actionCreateAccountFragmentStepSixToMainFragment())
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

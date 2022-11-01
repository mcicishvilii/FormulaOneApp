package com.example.formulaone.ui.navMenuFragments.settings

import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.R
import com.example.formulaone.ui.adapters.settings.HasButton
import com.example.formulaone.databinding.FragmentSettingsBinding
import com.example.formulaone.common.bases.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {
//    private val settingsAdapter: SettingsAdapter by lazy { SettingsAdapter() }


    private lateinit var auth: FirebaseAuth

    override fun viewCreated() {
        auth = Firebase.auth
        val user = auth.currentUser
        if (user != null) {
            binding.tvUsersName.text = auth.currentUser?.email.toString()
        }
        changeButton()
    }

    override fun listeners() {
        logOut()
        navigateLogIn()
    }


    private fun navigateLogIn(){
        binding.tvLogin.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToSignInFragment())
        }
    }

    private fun changeButton(){
        val user = auth.currentUser
        if (user == null){
            binding.logoutbutton.visibility = View.GONE
            binding.tvLogin.visibility = View.VISIBLE
        }
        else{
            binding.logoutbutton.visibility = View.VISIBLE
            binding.tvLogin.visibility = View.GONE
        }
    }

    private fun logOut() {
        binding.logoutbutton.setOnClickListener {
            auth.signOut()
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToWelcomeFragment())
            checkLoggedInState()
        }
    }

    private fun checkLoggedInState() {
        val user = auth.currentUser
        if (user == null) {
            binding.tvUsersName.text = ""
            Toast.makeText(requireContext(), "logged out", Toast.LENGTH_SHORT)
                .show()
        } else {
            binding.tvUsersName.text = auth.currentUser?.email.toString()
            Toast.makeText(requireContext(), "logged in", Toast.LENGTH_SHORT)
                .show()
        }
    }




}
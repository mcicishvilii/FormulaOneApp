package com.example.formulaone.ui.navMenuFragments.settings

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.DriversAdapter
import com.example.formulaone.R
import com.example.formulaone.SettingsAdapter
import com.example.formulaone.databinding.FragmentSettingsBinding
import com.example.formulaone.common.bases.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {
    private val settingsAdapter: SettingsAdapter by lazy { SettingsAdapter() }

    private lateinit var auth: FirebaseAuth
    override fun viewCreated() {
        setupRecycler()
        auth = Firebase.auth
        val user = auth.currentUser
        if(user != null){
            binding.test.text = auth.currentUser?.email.toString()
        }
    }

    override fun listeners() {
        logOut()
    }


    private fun logOut() {
        binding.test.setOnClickListener {
            auth.signOut()
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToFavoritesFragment())
            checkLoggedInState()
        }
    }

    private fun setupRecycler() {
        binding.rvSettings.apply {
            adapter = settingsAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }

    private fun checkLoggedInState() {
        val user = auth.currentUser
        if (user == null) {
            binding.test.text = ""
            Toast.makeText(requireContext(), "logged out", Toast.LENGTH_SHORT)
                .show()
        } else {
            binding.test.text = auth.currentUser?.email.toString()
            Toast.makeText(requireContext(), "logged in", Toast.LENGTH_SHORT)
                .show()
        }
    }


}
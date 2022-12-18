package com.example.formulaone.ui.navMenuFragments.settings

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.formulaone.data.model.firebase_test.ForTestFireBase
import com.example.formulaone.databinding.FragmentSettingsBinding
import com.example.formulaone.ui.adapters.LinksAdatper
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.common.bases.BaseFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


const val TAG = "mcicishvili"




@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate){
    private val linksAdapter: LinksAdatper by lazy { LinksAdatper() }


    private lateinit var mauth: FirebaseAuth

    override fun viewCreated() {
        mauth = Firebase.auth
        val user = mauth.currentUser
        if (user != null) {
            binding.tvUsersName.text = "hello dear \n${mauth.currentUser?.phoneNumber}"
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
        val user = mauth.currentUser
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
            mauth.signOut()
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToWelcomeFragment())
            checkLoggedInState()
        }
    }

    private fun checkLoggedInState() {
        val user = mauth.currentUser
        if (user == null) {
            binding.tvUsersName.text = ""
        } else {
            binding.tvUsersName.text = "hello  dear" + "  " + mauth.currentUser?.email.toString()
            Toast.makeText(requireContext(), "logged in", Toast.LENGTH_SHORT)
                .show()
        }
    }


}














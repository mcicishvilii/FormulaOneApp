package com.example.formulaone.ui.navMenuFragments.settings

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.databinding.FragmentSettingsBinding
import com.example.formulaone.ui.adapters.LinksAdatper
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.common.bases.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File
import java.io.InputStream

const val TAG = "misho"


@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {
    private val linksAdapter: LinksAdatper by lazy { LinksAdatper() }
    private val vm: SettingsViewModel by viewModels()

    private var read = false
    private var write = false
    private lateinit var permissonLauncher:ActivityResultLauncher<Array<String>>


    private lateinit var mauth: FirebaseAuth

    override fun viewCreated() {


        mauth = Firebase.auth
        val user = mauth.currentUser
        if (user != null) {
            binding.tvUsersName.text = "hello dear \n${mauth.currentUser?.email.toString()}"
        }
        changeButton()

        setupRecycler()
        observe()


    }

    override fun listeners() {
        logOut()
//        navigateLogIn()
        gotoLink()

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO){
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                binding.tvLogin.setOnClickListener {
//                    readText()
                }
            }
        }
    }


    private fun gotoLink() {
        linksAdapter.setOnItemClickListener { article, _ ->
            val uri: Uri = Uri.parse(article.link) // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    private fun navigateLogIn() {
        binding.tvLogin.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToSignInFragment())
        }
    }

    private fun changeButton() {
        val user = mauth.currentUser
        if (user == null) {
            binding.logoutbutton.visibility = View.GONE
            binding.tvLogin.visibility = View.VISIBLE
        } else {
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

    private fun observe() {
        setupRecycler()
        vm.getTeams()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.state.collectLatest {
                    when (it) {
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {
                            Log.d("cicishvili", it.loading.toString())
                        }
                        is Resource.Success -> {
                            linksAdapter.submitList(it.data)
                            Log.d("cicishvili", it.data.size.toString())
                        }
                    }
                }
            }
        }
    }

    private fun setupRecycler() {
        binding.rvLinks.apply {
            adapter = linksAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }


    private fun updateOrRequestPermissions(){
        val hasRead = ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        val hasWrite = ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        val minSdk29 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
        read = hasRead
        write = hasWrite || minSdk29

        val permissionsToRequest = mutableListOf<String>()

        if(!write){
            permissionsToRequest.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if(!read){
            permissionsToRequest.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if(permissionsToRequest.isNotEmpty()){
            permissonLauncher.launch(permissionsToRequest.toTypedArray())
        }
    }



}














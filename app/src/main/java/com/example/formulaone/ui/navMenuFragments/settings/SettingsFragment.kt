package com.example.formulaone.ui.navMenuFragments.settings

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {
    private val linksAdapter: LinksAdatper by lazy { LinksAdatper() }
    private val vm: SettingsViewModel by viewModels()


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
        navigateLogIn()
        gotoLink()

    }


    private fun gotoLink(){
        linksAdapter.setOnItemClickListener { article, _ ->
            val uri: Uri = Uri.parse(article.link) // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
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
                            Log.d("cicishvili",it.data.size.toString())
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


}
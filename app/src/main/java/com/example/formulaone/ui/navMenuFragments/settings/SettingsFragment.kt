package com.example.formulaone.ui.navMenuFragments.settings


import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formulaone.databinding.FragmentSettingsBinding
import com.example.formulaone.domain.model.LinksDomaini
import com.example.formulaone.ui.adapters.LinksAdatper
import com.example.formulaoneapplicationn.common.bases.BaseFragment
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    private val linksAdapter: LinksAdatper by lazy { LinksAdatper() }
    val linksList = mutableListOf<LinksDomaini>()

    private lateinit var mauth: FirebaseAuth

    override fun viewCreated() {
        mauth = Firebase.auth
        val user = mauth.currentUser
        if (user != null) {
            binding.tvUsersName.text = "hello dear \n${mauth.currentUser?.phoneNumber}"
        }
        changeButton()
        setupRecycler()
        popLinksList()

        linksAdapter.submitList(linksList)


    }

    override fun listeners() {
        logOut()
        navigateLogIn()
        gotoLink()
    }

    private fun gotoLink(){
        linksAdapter.setOnItemClickListener { article, _ ->
            val uri: Uri = Uri.parse(article.link)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
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


    fun popLinksList() {
        linksList.add(
            LinksDomaini(1, "https://motorsports-stream.com/live-races/")
        )
        linksList.add(
            LinksDomaini(2, "https://f1box.me/")
        )
    }

}














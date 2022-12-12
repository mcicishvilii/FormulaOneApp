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
import com.example.formulaone.data.model.firebase_test.ForTestFireBase
import com.example.formulaone.databinding.FragmentSettingsBinding
import com.example.formulaone.ui.adapters.LinksAdatper
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.common.bases.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
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
    private lateinit var permissonLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var database: DatabaseReference



    private lateinit var mauth: FirebaseAuth

    override fun viewCreated() {


        database = Firebase.database.reference
        mauth = Firebase.auth
        val user = mauth.currentUser
        if (user != null) {
            binding.tvUserInfo.text = "hello dear \n${mauth.currentUser?.email.toString()}"
        }


        changeButton()
//        observe()
        deleteAcc()
        writeNewUser()
        getUser()

    }

    private fun getUser(){
        binding.btnGet.setOnClickListener {


            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val userId = binding.etUserid.text.toString()
            val table = binding.etTable.text.toString()


            database.child(table).child(userId).child(name).get()
                .addOnSuccessListener {
                    binding.tvUserInfo.text = it.value.toString()
                }.addOnFailureListener {
                    Log.e("firebase", "Error getting data", it)
                }
        }
    }

    private fun deleteAcc() {
        binding.btnDelete.setOnClickListener {
            val userId = binding.etUserid.text.toString()
            val table = binding.etTable.text.toString()

            val momxmareblebisShvili = database.child(table).child(userId)

            momxmareblebisShvili.removeValue()
        }
    }

    fun writeNewUser() {
        binding.btnAdd.setOnClickListener{
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val userId = binding.etUserid.text.toString()
            val table = binding.etTable.text.toString()

            val user = ForTestFireBase(name,email)
            database.child(table).child(userId).setValue(user)
        }
    }

    override fun listeners() {



//        insertIntoDatabase()
//        getFromDataBase()
        logOut()
//        navigateLogIn()
//        gotoLink()

//        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                binding.tvUserInfo.setOnClickListener {
////                    readText()
//                }
//            }
//        }
    }

    private fun insertIntoDatabase() {
    }

    private fun getFromDataBase() {


    }

//    private fun gotoLink() {
//        linksAdapter.setOnItemClickListener { article, _ ->
//            val uri: Uri = Uri.parse(article.link) // missing 'http://' will cause crashed
//            val intent = Intent(Intent.ACTION_VIEW, uri)
//            startActivity(intent)
//        }
//    }

    private fun navigateLogIn() {
        binding.tvUserInfo.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToSignInFragment())
        }
    }

    private fun changeButton() {
        val user = mauth.currentUser
        if (user == null) {
            binding.logoutbutton.visibility = View.GONE
            binding.tvUserInfo.visibility = View.VISIBLE
        } else {
            binding.logoutbutton.visibility = View.VISIBLE
            binding.tvUserInfo.visibility = View.GONE
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
            binding.tvUserInfo.text = ""
        } else {
            binding.tvUserInfo.text = "hello  dear" + "  " + mauth.currentUser?.email.toString()
            Toast.makeText(requireContext(), "logged in", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun observe() {
//        setupRecycler()
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

//    private fun setupRecycler() {
//        binding.rvLinks.apply {
//            adapter = linksAdapter
//            layoutManager =
//                LinearLayoutManager(
//                    requireContext(),
//                    LinearLayoutManager.VERTICAL,
//                    false
//                )
//        }
//    }


    private fun updateOrRequestPermissions() {
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

        if (!write) {
            permissionsToRequest.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!read) {
            permissionsToRequest.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (permissionsToRequest.isNotEmpty()) {
            permissonLauncher.launch(permissionsToRequest.toTypedArray())
        }
    }


}














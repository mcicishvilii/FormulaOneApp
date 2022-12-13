package com.example.formulaone.ui.navMenuFragments.settings

import android.content.pm.PackageManager
import android.os.Build
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
import com.example.formulaone.data.model.firebase_test.ForTestFireBase
import com.example.formulaone.databinding.FragmentSettingsBinding
import com.example.formulaone.ui.adapters.LinksAdatper
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.common.bases.BaseFragment
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.safetynet.SafetyNet
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


const val TAG = "mcicishvili"
val siteKey = "6LfRT3cjAAAAAOcUnzmJRpsL3HPqb6vGSa_ip_fH"
val secretKey = "6LfRT3cjAAAAACrtLyDifiQqw7f4-Wbi3z0n0Cy0"


@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {
    private val linksAdapter: LinksAdatper by lazy { LinksAdatper() }
    private val vm: SettingsViewModel by viewModels()

    private lateinit var permissonLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var database: DatabaseReference
    private lateinit var mauth: FirebaseAuth

    lateinit var storedVerificationId: String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    private var read = false
    private var write = false

    var verificationId = ""



    override fun viewCreated() {
        database = Firebase.database.reference
        mauth = Firebase.auth
        val user = mauth.currentUser
        if (user != null) {
            binding.tvUserInfo.text = "hello dear \n${mauth.currentUser?.email.toString()}"
        }

        changeButton()
//        deleteAcc()
//        writeNewUser()

        getUser()
    }

    override fun listeners() {
        logOut()
    }


    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d("GFG", "onVerificationCompleted Success")
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Log.d("GFG", "onVerificationFailed  $e")
        }
        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            Log.d("GFG", "onCodeSent: $verificationId")
            storedVerificationId = verificationId
            resendToken = token
        }
    }



    private fun sendVerificationCode() {
        val options = PhoneAuthOptions.newBuilder(mauth)
            .setPhoneNumber("+995551585021") // Phone number to verify
            .setTimeout(120L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity()) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        Log.d("GFG" , "Auth started")
    }

    private fun getUser() {
        binding.btnGet.setOnClickListener {
            sendVerificationCode()
        }
    }

    private fun captcha(){

//        fun onClick() {
//            SafetyNet.getClient(this).verifyWithRecaptcha(YOUR_API_SITE_KEY)
//                .addOnSuccessListener(this as Executor, OnSuccessListener { response ->
//                    // Indicates communication with reCAPTCHA service was
//                    // successful.
//                    val userResponseToken = response.tokenResult
//                    if (response.tokenResult?.isNotEmpty() == true) {
//                        // Validate the user response token using the
//                        // reCAPTCHA siteverify API.
//                    }
//                })
//                .addOnFailureListener(this as Executor, OnFailureListener { e ->
//                    if (e is ApiException) {
//                        // An error occurred when communicating with the
//                        // reCAPTCHA service. Refer to the status code to
//                        // handle the error appropriately.
//                        Log.d(TAG, "Error: ${CommonStatusCodes.getStatusCodeString(e.statusCode)}")
//                    } else {
//                        // A different, unknown type of error occurred.
//                        Log.d(TAG, "Error: ${e.message}")
//                    }
//                })
//        }

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
        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val userId = binding.etUserid.text.toString()
            val table = binding.etTable.text.toString()

            val user = ForTestFireBase(name, email)
            database.child(table).child(userId).setValue(user)
        }
    }


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


}














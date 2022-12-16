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
val siteKey = "6LfRT3cjAAAAAOcUnzmJRpsL3HPqb6vGSa_ip_fH"
val secretKey = "6LfRT3cjAAAAACrtLyDifiQqw7f4-Wbi3z0n0Cy0"
var priority = ""


@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate){
    private val linksAdapter: LinksAdatper by lazy { LinksAdatper() }
    private val vm: SettingsViewModel by viewModels()

    private lateinit var mauth: FirebaseAuth

    // phone num auth
    var mVerificationId:String = ""
    lateinit var mResendToken: PhoneAuthProvider.ForceResendingToken
    // phone num auth



    // firebase db
    private lateinit var permissonLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var database: DatabaseReference
    private var read = false
    private var write = false
    // firebase db


    override fun viewCreated() {
        database = Firebase.database.reference
        mauth = Firebase.auth
        val user = mauth.currentUser
        if (user != null) {
            binding.tvUserInfo.text = "hello dear \n${mauth.currentUser?.email.toString()}"
        }

        changeButton()
        setupSpinner()
    }

    override fun listeners() {
        logOut()
        sendCode()
        checkVerificationCode()
        test()
    }

    private fun sendCode() {
        binding.btnGet.setOnClickListener {
            val number = binding.etPhoneNum.text.toString()
            sendVerificationCode(number)
        }
    }

    // step 1
    fun sendVerificationCode(number: String) {
        mauth = Firebase.auth
        val options = PhoneAuthOptions.newBuilder(mauth)
            .setPhoneNumber(number) // Phone number to verify
            .setTimeout(120L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity()) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        Log.d(TAG, "Auth started")
    }

    // step 2
    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            val code = credential.smsCode
            if (code != null) {
                binding.etEnterCode.setText(code)
                verifyCode(code)
            }
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show();
        }

        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken, ) {
            super.onCodeSent(verificationId,token)
            mVerificationId = verificationId
            mResendToken = token
        }
    }

    // step 3

    private fun verifyCode(code: String) {

        try {
            val credential = PhoneAuthProvider.getCredential(mVerificationId, code)
            signInWithPhoneAuthCredential(credential)
            Log.d(TAG,"verifyCode try block ${mauth.currentUser?.phoneNumber}")
            binding.tvUserInfo.text = mauth.currentUser?.phoneNumber
        }catch (e:Exception){
            Log.d(TAG,"verifyCode catch block ${e.message}")
        }

    }

    // step 4

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mauth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToWelcomeFragment())
            } else {
                Log.d(TAG,"signInWithPhoneAuthCredential else block ${mauth.currentUser}")
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(requireContext(),
                        "The verification code entered was invalid",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    // step 5
    private fun checkVerificationCode() {
        binding.btnAdd.setOnClickListener {
            if (binding.etEnterCode.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Please enter OTP", Toast.LENGTH_SHORT).show()
            } else {
                verifyCode(binding.etEnterCode.text.toString())
                Log.d(TAG,"fun blah else block ${mauth.currentUser?.phoneNumber}")
            }
        }
    }




//    private fun deleteAcc() {
//        binding.btnDelete.setOnClickListener {
//            val userId = binding.etUserid.text.toString()
//            val table = binding.etTable.text.toString()
//
//            val momxmareblebisShvili = database.child(table).child(userId)
//
//            momxmareblebisShvili.removeValue()
//        }
//    }

//    private fun writeNewUser() {
//        binding.btnAdd.setOnClickListener {
//            val name = binding.etName.text.toString()
//            val email = binding.etEmail.text.toString()
//            val userId = binding.etUserid.text.toString()
//            val table = binding.etTable.text.toString()
//
//            val user = ForTestFireBase(name, email)
//            database.child(table).child(userId).setValue(user)
//        }
//    }

//    private fun navigateLogIn() {
//        binding.tvUserInfo.setOnClickListener {
//            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToSignInFragment())
//        }
//    }

    private fun setupSpinner() {
        val priority = resources.getStringArray(com.example.formulaone.R.array.index)
        val adapter1 = ArrayAdapter(requireContext(),com.example.formulaone.R.layout.custom_spinner_layout,priority)
        binding.newspinner.setAdapter(adapter1)
    }

    private fun test(){
        binding.newspinner.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val splittedS = s?.dropLast(3)
                binding.etPhoneNum.setText(splittedS)
            }
        })
    }



//    override fun onNothingSelected(parent: AdapterView<*>?) {
//
//    }
//
//    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        val text: String = parent?.getItemAtPosition(position).toString()
////        priority = text
//        Snackbar.make(requireActivity(),binding.fragment,text,Snackbar.LENGTH_LONG).show()
//        binding.etPhoneNum.setText(text)
//
//    }

    private fun changeButton(){
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
            binding.tvUserInfo.text = "hello  dear" + "  " + mauth.currentUser?.phoneNumber
            Toast.makeText(requireContext(), "logged in", Toast.LENGTH_SHORT).show()
        }
    }

//    private fun observe() {
////        setupRecycler()
//        vm.getTeams()
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                vm.state.collectLatest {
//                    when (it) {
//                        is Resource.Error -> {
//
//                        }
//                        is Resource.Loading -> {
//                            Log.d("cicishvili", it.loading.toString())
//                        }
//                        is Resource.Success -> {
//                            linksAdapter.submitList(it.data)
//                            Log.d("cicishvili", it.data.size.toString())
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private fun updateOrRequestPermissions() {
//        val hasRead = ContextCompat.checkSelfPermission(
//            requireContext(),
//            android.Manifest.permission.READ_EXTERNAL_STORAGE
//        ) == PackageManager.PERMISSION_GRANTED
//
//        val hasWrite = ContextCompat.checkSelfPermission(
//            requireContext(),
//            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
//        ) == PackageManager.PERMISSION_GRANTED
//
//        val minSdk29 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
//        read = hasRead
//        write = hasWrite || minSdk29
//
//        val permissionsToRequest = mutableListOf<String>()
//
//        if (!write) {
//            permissionsToRequest.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
//        }
//        if (!read) {
//            permissionsToRequest.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
//        }
//        if (permissionsToRequest.isNotEmpty()) {
//            permissonLauncher.launch(permissionsToRequest.toTypedArray())
//        }
//    }

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














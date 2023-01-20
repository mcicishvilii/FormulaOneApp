package com.example.formulaone.ui.signInFragment

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentOtpSignInBinding
import com.example.formulaone.common.bases.BaseFragment
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

const val TAG = "MCICISHVILII"


class OtpSignInFragment : BaseFragment<FragmentOtpSignInBinding>(FragmentOtpSignInBinding::inflate) {

    val TAG = "mcicishvili"
    private val vm: OtpSignInViewModel by viewModels()
    private lateinit var mauth: FirebaseAuth

    // phone num auth
    var mVerificationId:String = ""
    lateinit var mResendToken: PhoneAuthProvider.ForceResendingToken
    // phone num auth


    override fun viewCreated() {
        mauth = Firebase.auth


        setupSpinner()
    }

    override fun listeners() {
        sendCode()
        checkVerificationCode()
        spinnerToEt()
        checkVisibility()
    }




    private fun checkVisibility(){
        binding.etPhoneNumImpl.doOnTextChanged { text, start, before, count ->
            binding.btnNext.visibility = View.VISIBLE
        }
        binding.textView.setOnClickListener {
            findNavController().navigate(OtpSignInFragmentDirections.actionOtpSignInFragmentToSignInFragment())
        }
    }



    private fun sendCode() {
        binding.btnGetCode.setOnClickListener {
            val number = binding.etPhoneNumImpl.text.toString()
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
    }

    // step 2
    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            val code = credential.smsCode
            if (code != null) {
                binding.etOTPimpl.setText(code)
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
//            binding.tvUserInfo.text = mauth.currentUser?.phoneNumber
        }catch (e:Exception){
        }

    }

    // step 4

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mauth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                findNavController().navigate(OtpSignInFragmentDirections.actionOtpSignInFragmentToMainFragment())
                Toast.makeText(requireContext(),
                    "Logged in with ${mauth.currentUser?.phoneNumber}",
                    Toast.LENGTH_SHORT).show()
            } else {
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
        binding.btnVerifyCode.setOnClickListener {
            if (binding.etOTPimpl.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Please enter OTP", Toast.LENGTH_SHORT).show()
            } else {
                verifyCode(binding.etOTPimpl.text.toString())
            }
        }
    }

    private fun setupSpinner() {
        val priority = resources.getStringArray(com.example.formulaone.R.array.index)
        val adapter1 = ArrayAdapter(requireContext(), R.layout.custom_spinner_layout,priority)
        binding.newspinner.setAdapter(adapter1)
    }

    private fun spinnerToEt(){
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
                binding.etPhoneNumImpl.setText(splittedS)
            }
        })
    }
}
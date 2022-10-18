package com.example.formulaone.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.formulaone.R
import com.example.formulaone.common.utils.Swipe
import com.example.formulaone.databinding.ActivityMainBinding
import com.example.formulaone.ui.mainFragment.MainFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        drawerListener()
    }



//    private fun drawerListener() {
//        binding?.drawer?.setOnTouchListener(object: Swipe(this@MainActivity) {
//            override fun onSwipeRight() {
//                startDrawer()
//            }
//        }
//        )
//    }
//
//
//    private fun startDrawer() {
//        binding?.drawer?.openDrawer(
//            GravityCompat.START, true
//        )
//    }
}
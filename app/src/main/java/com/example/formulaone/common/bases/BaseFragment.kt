package com.example.formulaoneapplicationn.common.bases

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflater <T> = (inflater: LayoutInflater, view: ViewGroup?, attach:Boolean) -> T

abstract class BaseFragment<VB: ViewBinding>(private val inflater: Inflater<VB>): Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    abstract fun viewCreated()
    abstract fun listeners()
//    abstract fun onCreate()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        Log.d("answe","onCreateView")
//        onCreate()
        _binding = this.inflater.invoke(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewCreated()
        listeners()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("answe","onDestroyView")
    }
}
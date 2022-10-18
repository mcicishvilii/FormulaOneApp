package com.example.formulaone.ui.navMenuFragments.drivers.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.formulaone.R
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaone.databinding.FragmentDriverDetailsBinding
import com.example.formulaone.ui.navMenuFragments.drivers.list.DriversFragment
import com.example.formulaone.ui.navMenuFragments.drivers.list.DriversFragmentDirections
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DriverDetailsFragment : BaseFragment<FragmentDriverDetailsBinding>(FragmentDriverDetailsBinding::inflate){
    val args: DriverDetailsFragmentArgs by navArgs()
    override fun viewCreated() {
        val name = args.details
        binding.tvDriverFirstName.text = name?.name
        binding.tvDriverLastName.text = name?.lastName
        binding.tvDriversTeamAndNumber.text = name?.carNumber + "   |   " + name?.team
        binding.tvWins.text = name?.wins
//        binding.textView2.text = name?.team

        when(name?.lastName){
            "Verstappen" -> binding.appCompatImageView.setImageResource(R.drawable.max_verstappen)
            "Pérez" -> binding.appCompatImageView.setImageResource(R.drawable.perez)
            "Leclerc" -> binding.appCompatImageView.setImageResource(R.drawable.leclerc)
            "Russell" -> binding.appCompatImageView.setImageResource(R.drawable.russell)
            "Sainz" -> binding.appCompatImageView.setImageResource(R.drawable.sainz)
            "Hamilton" -> binding.appCompatImageView.setImageResource(R.drawable.hamilton)
            "Norris" -> binding.appCompatImageView.setImageResource(R.drawable.norris)
            "Ocon" -> binding.appCompatImageView.setImageResource(R.drawable.ocon)
            "Alonso" -> binding.appCompatImageView.setImageResource(R.drawable.alonso)
            "Bottas" -> binding.appCompatImageView.setImageResource(R.drawable.bottas)
            "Vettel" -> binding.appCompatImageView.setImageResource(R.drawable.vettel)
            "Ricciardo" -> binding.appCompatImageView.setImageResource(R.drawable.ricciardo)
            "Gasly" -> binding.appCompatImageView.setImageResource(R.drawable.gasly)
            "Magnussen" -> binding.appCompatImageView.setImageResource(R.drawable.kevin_magnussen)
            "Stroll" -> binding.appCompatImageView.setImageResource(R.drawable.stroll)
            "Schumacher" -> binding.appCompatImageView.setImageResource(R.drawable.mick_schumacher)
            "Tsunoda" -> binding.appCompatImageView.setImageResource(R.drawable.tsunoda)
            "Zhou" -> binding.appCompatImageView.setImageResource(R.drawable.zhou)
            "Albon" -> binding.appCompatImageView.setImageResource(R.drawable.albon)
            "Latifi" -> binding.appCompatImageView.setImageResource(R.drawable.latifi)
            "de Vries" -> binding.appCompatImageView.setImageResource(R.drawable.de_vries)
            "Hülkenberg" -> binding.appCompatImageView.setImageResource(R.drawable.hulkenberg)
        }
    }

    override fun listeners() {

    }


}
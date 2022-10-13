package com.example.formulaone

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.formulaone.SettingsAdapter.Const.HASBUTTON
import com.example.formulaone.SettingsAdapter.Const.NOBUTTON
import com.example.formulaone.adapters.TeamsDiffCallBack
import com.example.formulaone.adapters.settings.HasButton
import com.example.formulaone.data.remote.drivers.drivers_standings.DriverStandingsDto
import com.example.formulaone.databinding.SingleDriverLayoutBinding
import com.example.formulaone.databinding.SingleSettingLayoutSignedInBinding
import com.example.formulaone.databinding.SingleSettingLayoutSignedOutBinding
import com.example.formulaone.databinding.SingleTeamLayoutBinding
import com.example.formulaone.domain.model.remote.TeamsDomain
import com.example.formulaone.ui.models.Settings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SettingsAdapter : ListAdapter<Settings,RecyclerView.ViewHolder>(SettingsDiffCallback()) {

    private lateinit var auth: FirebaseAuth

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return when (viewType) {
            HASBUTTON -> {
                val view =
                    SingleSettingLayoutSignedInBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                SettingsSignedInVH(view)
            }
            else -> {
                val view =
                    SingleSettingLayoutSignedOutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                SettingsSignedOutVH(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            1 ->(holder as SettingsSignedInVH).bind()
            else -> (holder as SettingsSignedOutVH).bind()
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).hasButton){
            HasButton.TRUE -> 1
            else -> 2
        }
    }

    inner class SettingsSignedOutVH(private val settingsSignedOut: SingleSettingLayoutSignedOutBinding) :
        RecyclerView.ViewHolder(settingsSignedOut.root) {
        private var model: Settings? = null
        fun bind() {
            auth = Firebase.auth
            settingsSignedOut.tvUsersName.text = auth.currentUser?.email
        }
    }

    inner class SettingsSignedInVH(private val settingsSignedIn: SingleSettingLayoutSignedInBinding) :
        RecyclerView.ViewHolder(settingsSignedIn.root) {
        private var model: Settings? = null
        fun bind() {
            settingsSignedIn.btnLogOut.text = "Log Out"
        }
    }

    private object Const{
        const val HASBUTTON = 0
        const val NOBUTTON = 1
    }

    class SettingsDiffCallback : DiffUtil.ItemCallback<Settings>() {
        override fun areItemsTheSame(
            oldItem: Settings,
            newItem: Settings
        ): Boolean {
            return oldItem.userName == newItem.userName
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: Settings,
            newItem: Settings
        ): Boolean {
            return oldItem == newItem
        }
    }

}




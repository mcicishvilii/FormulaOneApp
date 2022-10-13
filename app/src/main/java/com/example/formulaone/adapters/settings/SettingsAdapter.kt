//package com.example.formulaone.adapters.settings
//
//import android.annotation.SuppressLint
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.formulaone.adapters.ConstructorsAdapter
//import com.example.formulaone.adapters.settings.SettingsAdapter.Const.HASBUTTON
//import com.example.formulaone.adapters.settings.SettingsAdapter.Const.NOBUTTON
//import com.example.formulaone.databinding.SingleSettingLayoutSignedInBinding
//import com.example.formulaone.databinding.SingleSettingLayoutSignedOutBinding
//import com.example.formulaone.databinding.SingleTeamLayoutBinding
//import com.example.formulaone.ui.models.Settings
//
//class SettingsAdapter : ListAdapter<Settings, RecyclerView.ViewHolder>(SettingsDiffCallback()) {
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup, viewType: Int
//    ): ConstructorsAdapter.ConstructorsViewHolder {
//        val binding =
//            SingleTeamLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ConstructorsViewHolder(binding)
//    }
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        holder.bindData()
//    }
//
//
//
//
//    class SettingsDiffCallback : DiffUtil.ItemCallback<Settings>() {
//        override fun areItemsTheSame(
//            oldItem: Settings,
//            newItem: Settings
//        ): Boolean {
//            return oldItem.userName == newItem.userName
//        }
//
//        @SuppressLint("DiffUtilEquals")
//        override fun areContentsTheSame(
//            oldItem: Settings,
//            newItem: Settings
//        ): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//
//
//}
//
//
//

package com.example.formulaone.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.formulaone.R
import com.example.formulaone.data.remote.drivers.plugin.DriverStandingsDto
import com.example.formulaone.databinding.SingleFavTeamLayoutBinding

class FavTeamsAdapter: ListAdapter<DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding, FavTeamsAdapter.FavTeamsViewHolder>(
    FavTeamsDiffCallback()
) {

//    private lateinit var itemClickListener: (PopularResponse.Result1, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): FavTeamsViewHolder {
        val binding =
            SingleFavTeamLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavTeamsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavTeamsViewHolder, position: Int) {
        holder.bindData()
    }

    inner class FavTeamsViewHolder(private val binding: SingleFavTeamLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding? = null
        fun bindData() {
            model = getItem(bindingAdapterPosition)
            binding.apply {
                tvTeamName.text = model?.Driver?.givenName + model?.Driver?.familyName
                tvTeamNationality.text = "${model?.position})"


                binding.root.setBackgroundResource(R.drawable.outline)

            }
        }

//    fun setOnItemClickListener(clickListener: (PopularResponse.Result1, Int) -> Unit) {
//        itemClickListener = clickListener
//    }
    }
}
class FavTeamsDiffCallback : DiffUtil.ItemCallback<DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding>() {
    override fun areItemsTheSame(oldItem: DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding, newItem: DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding
    ): Boolean {
        return oldItem.Driver.driverId == newItem.Driver.driverId
    }

    override fun areContentsTheSame(oldItem: DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding, newItem: DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding
    ): Boolean {
        return oldItem == newItem
    }
}
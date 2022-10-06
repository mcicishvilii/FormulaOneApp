package com.example.formulaone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.formulaone.data.remote.drivers.drivers_standings.DriverStandingsDto
import com.example.formulaone.databinding.SingleDriverLayoutBinding

class DriversAdapter: ListAdapter<DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding, DriversAdapter.DriversViewHolder>(DriversDiffCallBack()) {

//    private lateinit var itemClickListener: (PopularResponse.Result1, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): DriversViewHolder {
        val binding =
            SingleDriverLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DriversViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DriversViewHolder, position: Int) {
        holder.bindData()
    }

    inner class DriversViewHolder(private val binding: SingleDriverLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding? = null
        fun bindData() {
            model = getItem(bindingAdapterPosition)
            binding.apply {
                tvDriverName.text = model?.Driver?.givenName + model?.Driver?.familyName
                tvPosition.text = "${model?.position})"
                tvPoints.text = "${model?.points} pts"

                binding.root.setBackgroundResource(R.drawable.outline)

            }
        }

//    fun setOnItemClickListener(clickListener: (PopularResponse.Result1, Int) -> Unit) {
//        itemClickListener = clickListener
//    }
    }
}
class DriversDiffCallBack : DiffUtil.ItemCallback<DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding>() {
    override fun areItemsTheSame(oldItem: DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding, newItem: DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding
    ): Boolean {
        return oldItem.Driver.driverId == newItem.Driver.driverId
    }

    override fun areContentsTheSame(oldItem: DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding, newItem: DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding
    ): Boolean {
        return oldItem == newItem
    }
}
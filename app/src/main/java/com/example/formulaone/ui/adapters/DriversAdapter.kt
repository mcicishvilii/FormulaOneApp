package com.example.formulaone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.formulaone.data.remote.drivers.drivers_standings.DriverStandingsDto
import com.example.formulaone.databinding.SingleDriverLayoutBinding

class DriversAdapter :
    ListAdapter<DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding, DriversAdapter.DriversViewHolder>(
        DriversDiffCallBack()
    ) {

    private lateinit var itemClickListener: (DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding, Int) -> Unit

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
        private var model: DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding? =
            null

        fun bindData() {
            model = getItem(bindingAdapterPosition)
            binding.apply {

                tvDriverName.text = model?.Driver?.givenName
                tvDriverLastName.text = model?.Driver?.familyName
                tvPosition.text = "${model?.position}"
                tvPoints.text = "${model?.points} pts"
                tvTeam.text = model?.Constructors!![0].name


                when (model?.Constructors!![0].constructorId) {
                    "red_bull" -> ivTeamIndicator.setImageResource(R.color.Blue)
                    "ferrari" -> ivTeamIndicator.setImageResource(R.color.Red)
                    "mercedes" -> ivTeamIndicator.setImageResource(R.color.LightGreen)
                    "mclaren" -> ivTeamIndicator.setImageResource(R.color.Orange)
                    "alpine" -> ivTeamIndicator.setImageResource(R.color.HotPink)
                    "alfa" -> ivTeamIndicator.setImageResource(R.color.DarkRed)
                    "aston_martin" -> ivTeamIndicator.setImageResource(R.color.green)
                    "alphatauri" -> ivTeamIndicator.setImageResource(R.color.CadetBlue)
                    "haas" -> ivTeamIndicator.setImageResource(R.color.gray)
                    "williams" -> ivTeamIndicator.setImageResource(R.color.Aqua)
                }


            }
            binding.forwardArrow.setOnClickListener {
                itemClickListener.invoke(model!!, absoluteAdapterPosition)
            }


        }


    }
    fun setOnItemClickListener(clickListener: (DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding, Int) -> Unit) {
        itemClickListener = clickListener
    }
}

class DriversDiffCallBack :
    DiffUtil.ItemCallback<DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding>() {
    override fun areItemsTheSame(
        oldItem: DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding,
        newItem: DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding
    ): Boolean {
        return oldItem.Driver.driverId == newItem.Driver.driverId
    }

    override fun areContentsTheSame(
        oldItem: DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding,
        newItem: DriverStandingsDto.MRDataX.StandingsTableX.StandingsListsX.DriverStanding
    ): Boolean {
        return oldItem == newItem
    }


}
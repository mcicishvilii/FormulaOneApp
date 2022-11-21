package com.example.formulaone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.formulaone.databinding.SingleDriverLayoutBinding
import com.example.formulaoneapplicationn.domain.model.DriverStandingDomain

class DriversAdapter :
    ListAdapter<DriverStandingDomain, DriversAdapter.DriversViewHolder>(
        DriversDiffCallBack()
    ) {

    private lateinit var itemClickListener: (DriverStandingDomain, Int) -> Unit

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
        private var model: DriverStandingDomain? =
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

    fun setOnItemClickListener(clickListener: (DriverStandingDomain, Int) -> Unit) {
        itemClickListener = clickListener
    }
}

class DriversDiffCallBack :
    DiffUtil.ItemCallback<DriverStandingDomain>() {
    override fun areItemsTheSame(
        oldItem: DriverStandingDomain,
        newItem: DriverStandingDomain
    ): Boolean {
        return oldItem.Driver.driverId == newItem.Driver.driverId
    }

    override fun areContentsTheSame(
        oldItem: DriverStandingDomain,
        newItem: DriverStandingDomain
    ): Boolean {
        return oldItem == newItem
    }


}
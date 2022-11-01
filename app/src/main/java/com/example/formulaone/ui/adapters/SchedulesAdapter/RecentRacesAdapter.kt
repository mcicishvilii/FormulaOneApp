package com.example.formulaone.ui.adapters.SchedulesAdapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.formulaone.R
import com.example.formulaone.databinding.SingleRecentRaceBinding
import com.example.formulaone.domain.model.remote.RaceDomain
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RecentRacesAdapter :
    ListAdapter<RaceDomain, RecentRacesAdapter.RecentRaceViewHolder>(
        RecentRacesDiffCallback()
    ) {


    inner class RecentRaceViewHolder(private val binding: SingleRecentRaceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: RaceDomain? = null

        @RequiresApi(Build.VERSION_CODES.O)
        fun bindData() {
            model = getItem(bindingAdapterPosition)

            val dateFromModel = model!!.date
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val date = LocalDate.parse(dateFromModel, formatter)
            val splittedDate = date.month.name.split("")
            val accronymDate = "${splittedDate[1]}${splittedDate[2]}${splittedDate[3]}"

            val droppedDays = date.toString().drop(8)

            binding.apply {
//
                binding.tvFirstPlace.text = model!!.Results[0].Driver.code
                binding.tvSecondPlace.text = model!!.Results[1].Driver.code
                binding.tvThirdPlace.text = model!!.Results[2].Driver.code

                tvCountry.text = model?.Circuit?.Location?.country
                tvDate.text = "$droppedDays\n$accronymDate"
                tvRound.text = "Round ${model?.round}"
                tvGrandPrixName.text = model?.raceName
                binding.root.setBackgroundResource(R.drawable.outline)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentRaceViewHolder {
        val binding =
            SingleRecentRaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentRaceViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecentRaceViewHolder, position: Int) {
        holder.bindData()
    }
}

class RecentRacesDiffCallback : DiffUtil.ItemCallback<RaceDomain>() {
    override fun areItemsTheSame(
        oldItem: RaceDomain,
        newItem: RaceDomain
    ): Boolean {
        return oldItem.date == newItem.date
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: RaceDomain,
        newItem: RaceDomain
    ): Boolean {
        return oldItem == newItem
    }
}
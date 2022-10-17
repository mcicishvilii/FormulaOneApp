package com.example.formulaone.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.formulaone.R
import com.example.formulaone.databinding.SingleRecentRaceBinding
import com.example.formulaone.databinding.SingleTeamLayoutBinding
import com.example.formulaone.databinding.SingleUpcomingRaceBinding
import com.example.formulaone.domain.model.remote.RaceScheduleDomain
import com.example.formulaone.domain.model.remote.TeamsDomain
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class RecentRacesAdapter :
    ListAdapter<RaceScheduleDomain, RecentRacesAdapter.RecentRaceViewHolder>(
        RecentRacesDiffCallback()
    ) {


    inner class RecentRaceViewHolder(private val binding: SingleRecentRaceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: RaceScheduleDomain? = null

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

                tvCountry.text = model?.Circuit?.circuitName
                tvDate.text = "$droppedDays\n$accronymDate"
                tvRound.text = model?.round
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

class RecentRacesDiffCallback : DiffUtil.ItemCallback<RaceScheduleDomain>() {
    override fun areItemsTheSame(
        oldItem: RaceScheduleDomain,
        newItem: RaceScheduleDomain
    ): Boolean {
        return oldItem.date == newItem.date
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: RaceScheduleDomain,
        newItem: RaceScheduleDomain
    ): Boolean {
        return oldItem == newItem
    }
}
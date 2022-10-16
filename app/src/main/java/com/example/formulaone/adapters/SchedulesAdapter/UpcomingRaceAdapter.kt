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
import com.example.formulaone.databinding.SingleTeamLayoutBinding
import com.example.formulaone.databinding.SingleUpcomingRaceBinding
import com.example.formulaone.domain.model.remote.RaceScheduleDomain
import com.example.formulaone.domain.model.remote.TeamsDomain
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class UpcomingRaceAdapter :
    ListAdapter<RaceScheduleDomain, UpcomingRaceAdapter.UpcomingRaceViewHolder>(
        UpcomingDiffCallBack()
    ){



    inner class UpcomingRaceViewHolder(private val binding: SingleUpcomingRaceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: RaceScheduleDomain? = null

        @RequiresApi(Build.VERSION_CODES.O)
        fun bindData() {
            model = getItem(bindingAdapterPosition)

            val time = Calendar.getInstance().time
            val formatterCurrentTime = SimpleDateFormat("yyyy-MM-dd")
            val formatterNow = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val currentTime = formatterCurrentTime.format(time)
            val dateNow = LocalDate.parse(currentTime, formatterNow)


            val dateFromModel = model?.date
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val date = LocalDate.parse(dateFromModel, formatter)

            if (date.toEpochDay() > dateNow.toEpochDay()){
                binding.apply {
                    tvCountry.text = model?.Circuit?.circuitName
                    tvDate.text = model?.date
                    tvRound.text = model?.round
                    tvGrandPrixName.text = model?.raceName
                    binding.root.setBackgroundResource(R.drawable.outline)
                }
            }else{
                Log.d("misho","misho")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingRaceViewHolder {
        val binding =
            SingleUpcomingRaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingRaceViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: UpcomingRaceViewHolder, position: Int) {
        holder.bindData()
    }
}

class UpcomingDiffCallBack : DiffUtil.ItemCallback<RaceScheduleDomain>() {
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
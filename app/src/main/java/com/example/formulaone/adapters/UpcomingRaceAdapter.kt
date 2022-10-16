package com.example.formulaone.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.formulaone.R
import com.example.formulaone.databinding.SingleTeamLayoutBinding
import com.example.formulaone.databinding.SingleUpcomingRaceBinding
import com.example.formulaone.domain.model.remote.RaceScheduleDomain
import com.example.formulaone.domain.model.remote.TeamsDomain

class UpcomingRaceAdapter :
    ListAdapter<RaceScheduleDomain, UpcomingRaceAdapter.UpcomingRaceViewHolder>(
        UpcomingDiffCallBack()
    ){
    inner class UpcomingRaceViewHolder(private val binding: SingleUpcomingRaceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: RaceScheduleDomain? = null
        fun bindData() {
            model = getItem(bindingAdapterPosition)
            binding.apply {
                tvCountry.text = model?.Circuit.toString()
                tvDate.text = model?.date
                tvRound.text = model?.round
                tvGrandPrixName.text = model?.raceName

                binding.root.setBackgroundResource(R.drawable.outline)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingRaceViewHolder {
        val binding =
            SingleUpcomingRaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingRaceViewHolder(binding)
    }

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
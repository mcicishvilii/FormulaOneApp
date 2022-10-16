package com.example.formulaone.adapters

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.formulaone.R
import com.example.formulaone.databinding.SingleTeamLayoutBinding
import com.example.formulaone.databinding.SingleUpcomingRaceBinding
import com.example.formulaone.domain.model.remote.RaceScheduleDomain
import com.example.formulaone.domain.model.remote.TeamsDomain

class UpcomingRaceAdapter :
    ListAdapter<RaceScheduleDomain, UpcomingRaceAdapter.UpcomingRaceViewHolder>(
        TeamsDiffCallBack()
    ){
    inner class UpcomingRaceViewHolder(private val binding: SingleUpcomingRaceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: RaceScheduleDomain? = null
        fun bindData() {
            model = getItem(bindingAdapterPosition)
            binding.apply {
                tvCountry.text = model.
                tvTeamNationality.text = model?.nationality

                binding.root.setBackgroundResource(R.drawable.outline)
            }

            binding.tvTeamName.setOnClickListener {
                itemClickListener.invoke(model!!, absoluteAdapterPosition)
            }

        }

    }
}
package com.example.formulaone.ui.adapters.SchedulesAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.formulaone.R
import com.example.formulaone.data.local.Tickets
import com.example.formulaone.data.local.models.TicketsEntity
import com.example.formulaone.databinding.SigleTicketBinding
import com.example.formulaone.databinding.SingleTeamLayoutBinding
import com.example.formulaone.domain.model.remote.TeamsDomain


class BoughtTIcketsAdapter :
    ListAdapter<TicketsEntity, BoughtTIcketsAdapter.BoughtTicketsViewHolder>(
        TeamsDiffCallBack()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): BoughtTicketsViewHolder {
        val binding =
            SigleTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoughtTicketsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BoughtTicketsViewHolder, position: Int) {
        holder.bindData()
    }


    inner class BoughtTicketsViewHolder(private val binding: SigleTicketBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: TicketsEntity? = null
        fun bindData() {
            model = getItem(bindingAdapterPosition)
            binding.apply {
                tvTrackName.text = model?.raceName

                when (model?.raceName) {
                    "Brazilian Grand Prix" -> ticketcontainer.setImageResource(R.drawable.interlagos)
                    "Abu Dhabi Grand Prix" -> ticketcontainer.setImageResource(R.drawable.yas_marina)
                }


                binding.root.setBackgroundResource(R.drawable.outline)
            }
        }
    }
}



class TeamsDiffCallBack : DiffUtil.ItemCallback<TicketsEntity>() {
    override fun areItemsTheSame(
        oldItem: TicketsEntity,
        newItem: TicketsEntity
    ): Boolean {
        return oldItem.ticketId == newItem.ticketId
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: TicketsEntity,
        newItem: TicketsEntity
    ): Boolean {
        return oldItem == newItem
    }
}
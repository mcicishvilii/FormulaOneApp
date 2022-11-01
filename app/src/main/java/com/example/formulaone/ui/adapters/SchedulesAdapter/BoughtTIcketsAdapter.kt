package com.example.formulaone.ui.adapters.SchedulesAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.formulaone.R
import com.example.formulaone.data.local.Tickets
import com.example.formulaone.databinding.SigleTicketBinding
import com.example.formulaone.databinding.SingleTeamLayoutBinding
import com.example.formulaone.domain.model.remote.TeamsDomain


class BoughtTIcketsAdapter :
    ListAdapter<Tickets, BoughtTIcketsAdapter.BoughtTicketsViewHolder>(
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
        private var model: Tickets? = null
        fun bindData() {
            model = getItem(bindingAdapterPosition)
            binding.apply {
                tvTrackName.text = model?.ticketType

//                when (model?.nationality) {
//                    "Swiss" -> ivTeamIndicator.setImageResource(R.color.red)
//                    "German" -> ivTeamIndicator.setImageResource(R.color.black)
//                    "American" -> ivTeamIndicator.setImageResource(R.color.Blue)
//                    "French" -> ivTeamIndicator.setImageResource(R.color.DarkBlue)
//                    "Italian" -> ivTeamIndicator.setImageResource(R.color.green)
//                    "British" -> ivTeamIndicator.setImageResource(R.color.OrangeRed)
//                    "Dutch" -> ivTeamIndicator.setImageResource(R.color.YellowGreen)
//                }


                binding.root.setBackgroundResource(R.drawable.outline)
            }
        }
    }
}



class TeamsDiffCallBack : DiffUtil.ItemCallback<Tickets>() {
    override fun areItemsTheSame(
        oldItem: Tickets,
        newItem: Tickets
    ): Boolean {
        return oldItem.ticketID == newItem.ticketID
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: Tickets,
        newItem: Tickets
    ): Boolean {
        return oldItem == newItem
    }
}
package com.example.formulaone.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.formulaone.R
import com.example.formulaone.databinding.SingleTeamLayoutBinding
import com.example.formulaone.domain.model.TeamsDomain


class ConstructorsAdapter :
    ListAdapter<TeamsDomain, ConstructorsAdapter.ConstructorsViewHolder>(
        TeamsDiffCallBack()
    ) {

    private lateinit var itemClickListener: (TeamsDomain, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ConstructorsViewHolder {
        val binding =
            SingleTeamLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConstructorsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConstructorsViewHolder, position: Int) {
        holder.bindData()
    }


    inner class ConstructorsViewHolder(private val binding: SingleTeamLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: TeamsDomain? = null
        fun bindData() {
            model = getItem(bindingAdapterPosition)
            binding.apply {
                tvTeamName.text = model?.name
                tvTeamNationality.text = model?.nationality

                when (model?.nationality) {
                    "Swiss" -> ivTeamIndicator.setImageResource(R.color.red)
                    "German" -> ivTeamIndicator.setImageResource(R.color.black)
                    "American" -> ivTeamIndicator.setImageResource(R.color.Blue)
                    "French" -> ivTeamIndicator.setImageResource(R.color.DarkBlue)
                    "Italian" -> ivTeamIndicator.setImageResource(R.color.green)
                    "British" -> ivTeamIndicator.setImageResource(R.color.OrangeRed)
                    "Dutch" -> ivTeamIndicator.setImageResource(R.color.YellowGreen)
                }


                binding.root.setBackgroundResource(R.drawable.outline)
            }

            binding.tvTeamName.setOnClickListener {
                itemClickListener.invoke(model!!, absoluteAdapterPosition)
            }

        }

    }

    fun setOnItemClickListener(clickListener: (TeamsDomain, Int) -> Unit) {
        itemClickListener = clickListener
    }
}



class TeamsDiffCallBack : DiffUtil.ItemCallback<TeamsDomain>() {
    override fun areItemsTheSame(
        oldItem: TeamsDomain,
        newItem: TeamsDomain
    ): Boolean {
        return oldItem.constructorId == newItem.constructorId
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: TeamsDomain,
        newItem: TeamsDomain
    ): Boolean {
        return oldItem == newItem
    }
}
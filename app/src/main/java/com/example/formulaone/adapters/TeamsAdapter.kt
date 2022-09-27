package com.example.formulaone

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.formulaone.data.remote.teams.Teams
import com.example.formulaone.databinding.SingleTeamLayoutBinding
import com.example.formulaone.domain.model.remote.TeamsDomain


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
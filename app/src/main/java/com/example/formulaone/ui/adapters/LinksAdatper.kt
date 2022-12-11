package com.example.formulaone.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.formulaone.databinding.SingleLinkLayoutBinding
import com.example.formulaone.domain.model.LinksDomain


class LinksAdatper :
    ListAdapter<LinksDomain, LinksAdatper.LinksViewHolder>(
        LinksDiffCallback()
    ) {

    private lateinit var itemClickListener: (LinksDomain, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ): LinksViewHolder {
        val binding =
            SingleLinkLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LinksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LinksViewHolder, position: Int) {
        holder.bindData()
    }


    inner class LinksViewHolder(private val binding: SingleLinkLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: LinksDomain? = null
        fun bindData() {
            model = getItem(bindingAdapterPosition)
            binding.apply {
                tvLink.text = model?.link
//                binding.root.setBackgroundResource(R.drawable.outline)
            }

            binding.tvLink.setOnClickListener {
                itemClickListener.invoke(model!!, absoluteAdapterPosition)
            }

        }

    }

    fun setOnItemClickListener(clickListener: (LinksDomain, Int) -> Unit) {
        itemClickListener = clickListener
    }
}


class LinksDiffCallback : DiffUtil.ItemCallback<LinksDomain>() {
    override fun areItemsTheSame(
        oldItem: LinksDomain,
        newItem: LinksDomain,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: LinksDomain,
        newItem: LinksDomain,
    ): Boolean {
        return oldItem == newItem
    }
}
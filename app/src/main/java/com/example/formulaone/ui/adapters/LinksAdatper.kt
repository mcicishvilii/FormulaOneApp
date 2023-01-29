package com.example.formulaone.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.formulaone.R
import com.example.formulaone.databinding.SingleLinkLayoutBinding
import com.example.formulaone.domain.model.LinksDomaini


class LinksAdatper :
    ListAdapter<LinksDomaini, LinksAdatper.LinksViewHolder>(
        LinksDiffCallback()
    ) {

    private lateinit var itemClickListener: (LinksDomaini, Int) -> Unit

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
        private var model: LinksDomaini? = null
        fun bindData() {
            model = getItem(bindingAdapterPosition)
            binding.apply {

                val string = model?.link?.removeRange(0,8)?.dropLast(1)
                tvLink.text = string
            }

            binding.tvLink.setOnClickListener {
                itemClickListener.invoke(model!!, absoluteAdapterPosition)
            }

        }

    }

    fun setOnItemClickListener(clickListener: (LinksDomaini, Int) -> Unit) {
        itemClickListener = clickListener
    }
}


class LinksDiffCallback : DiffUtil.ItemCallback<LinksDomaini>() {
    override fun areItemsTheSame(
        oldItem: LinksDomaini,
        newItem: LinksDomaini,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: LinksDomaini,
        newItem: LinksDomaini,
    ): Boolean {
        return oldItem == newItem
    }
}
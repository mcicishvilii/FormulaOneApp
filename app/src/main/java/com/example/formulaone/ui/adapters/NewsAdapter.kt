package com.example.formulaone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.formulaone.R
import com.example.formulaone.databinding.SingleNewsLayoutBinding
import com.example.formulaone.domain.model.LinksDomain
import com.example.formulaoneapplicationn.domain.model.ArticleDomain

class NewsAdapter() :
    PagingDataAdapter<ArticleDomain, NewsAdapter.PlayersViewHolder>(
        PlayersDiffCallback()
    ) {

    private lateinit var itemClickListener: (ArticleDomain, Int) -> Unit

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {

        val data = getItem(position)

        holder.bind(data)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {

        return PlayersViewHolder(
            SingleNewsLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    inner class PlayersViewHolder(
        private val binding: SingleNewsLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ArticleDomain?) {

            binding.apply {
                tvNewsText.text = data?.title
                tvDesription.text = data?.description

                Glide.with(this.ivNewsImage)
                    .load(data?.urlToImage)
                    .into(ivNewsImage)
            }

            binding.tvDesription.setOnClickListener {
                itemClickListener.invoke(data!!, absoluteAdapterPosition)
            }

        }

        fun setOnItemClickListener(clickListener: (ArticleDomain, Int) -> Unit) {
            itemClickListener = clickListener
        }
    }


    private class PlayersDiffCallback : DiffUtil.ItemCallback<ArticleDomain>() {
        override fun areItemsTheSame(oldItem: ArticleDomain, newItem: ArticleDomain): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ArticleDomain, newItem: ArticleDomain): Boolean {
            return oldItem == newItem
        }
    }

}
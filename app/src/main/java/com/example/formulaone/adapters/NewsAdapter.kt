package com.example.formulaone.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.formulaone.R
import com.example.formulaone.data.remote.drivers.drivers_standings.DriverStandingsDto
import com.example.formulaone.data.remote.news.NewsDto
import com.example.formulaone.data.remote.news.new_api.F1NewsDto
import com.example.formulaone.databinding.SingleDriverLayoutBinding
import com.example.formulaone.databinding.SingleNewsLayoutBinding
import com.example.formulaone.domain.model.remote.ArticleDomain

class NewsAdapter :
    ListAdapter<ArticleDomain, NewsAdapter.NewsViewHolder>(
        NewsDiffCallBack()
    ) {


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): NewsViewHolder {
        val binding =
            SingleNewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindData()
    }

    inner class NewsViewHolder(private val binding: SingleNewsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: ArticleDomain? = null

        fun bindData() {
            model = getItem(bindingAdapterPosition)
            binding.apply {
                tvNewsText.text = model?.description
                Glide.with(this.ivNewsImage)
                    .load(model?.urlToImage)
                    .into(ivNewsImage)
            }
        }
    }

}

class NewsDiffCallBack :
    DiffUtil.ItemCallback<ArticleDomain>() {
    override fun areItemsTheSame(
        oldItem: ArticleDomain,
        newItem: ArticleDomain
    ): Boolean {
        return oldItem.content == newItem.content
    }

    override fun areContentsTheSame(
        oldItem: ArticleDomain,
        newItem: ArticleDomain
    ): Boolean {
        return oldItem == newItem
    }


}
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
import com.example.formulaone.databinding.SingleDriverLayoutBinding
import com.example.formulaone.databinding.SingleNewsLayoutBinding

class NewsAdapter :
    ListAdapter<NewsDto, NewsAdapter.NewsViewHolder>(
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
        private var model: NewsDto? = null

        fun bindData() {
            model = getItem(bindingAdapterPosition)
            binding.apply {
                tvNewsText.text = model?.newsDtItem!![0].title

                Glide.with(this.ivNewsImage)
                    .load(model?.newsDtItem!![0].imgsrc)
                    .into(ivNewsImage)

            }
        }
    }

}

class NewsDiffCallBack :
    DiffUtil.ItemCallback<NewsDto>() {
    override fun areItemsTheSame(
        oldItem: NewsDto,
        newItem: NewsDto
    ): Boolean {
        return oldItem.newsDtItem[0].shortdesc == newItem.newsDtItem[0].shortdesc
    }

    override fun areContentsTheSame(
        oldItem: NewsDto,
        newItem: NewsDto
    ): Boolean {
        return oldItem == newItem
    }


}
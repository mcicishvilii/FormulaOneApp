package com.example.formulaone.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.formulaone.DriversAdapter
import com.example.formulaone.R
import com.example.formulaone.data.Recyclers
import com.example.formulaone.data.remote.drivers.drivers_standings.DriverStandingsDto
import com.example.formulaone.databinding.SingleDriverLayoutBinding
import com.example.formulaone.databinding.SingleRecyclerViewBinding

class ViewPagerAdapter(
    val model:List<Recyclers>
) : Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(private val binding: SingleRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: Recyclers? = null
        fun bindData(){
            binding.recyclerView.addView(model?.recentRaces)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewPagerAdapter.ViewPagerViewHolder {
        val binding =
            SingleRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bindData()
    }

    override fun getItemCount(): Int {
        return model.size
    }


}

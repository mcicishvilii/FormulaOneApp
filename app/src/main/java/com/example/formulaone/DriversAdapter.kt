//package com.example.formulaone
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.formulaone.databinding.SingleDriverLayoutBinding
//
//class DriversAdapter: ListAdapter<Driver, DriversAdapter.DriversViewHolder>(DriversDiffCallBack()) {
//
////    private lateinit var itemClickListener: (PopularResponse.Result1, Int) -> Unit
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup, viewType: Int
//    ): DriversViewHolder {
//        val binding =
//            SingleDriverLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return DriversViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: DriversViewHolder, position: Int) {
//        holder.bindData()
//    }
//
//
//    inner class DriversViewHolder(private val binding: SingleDriverLayoutBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        private var model: Driver? = null
//        fun bindData() {
//            model = getItem(bindingAdapterPosition)
//            binding.apply {
//                tvDriverName.text = model?.givenName
//                tvDriverLastName.text = model?.familyName
//
//                binding.root.setBackgroundResource(R.drawable.outline)
//
//            }
//        }
//
////    fun setOnItemClickListener(clickListener: (PopularResponse.Result1, Int) -> Unit) {
////        itemClickListener = clickListener
////    }
//    }
//}
//class DriversDiffCallBack : DiffUtil.ItemCallback<Driver>() {
//    override fun areItemsTheSame(oldItem: Driver, newItem: Driver
//    ): Boolean {
//        return oldItem.driverId == newItem.driverId
//    }
//
//    override fun areContentsTheSame(oldItem: Driver, newItem: Driver
//    ): Boolean {
//        return oldItem == newItem
//    }
//}
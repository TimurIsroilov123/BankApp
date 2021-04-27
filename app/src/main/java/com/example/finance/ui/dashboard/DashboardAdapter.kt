package com.example.finance.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finance.data.Model
import com.example.finance.databinding.ItemRecyclerDashboardBinding

class DashboardAdapter : ListAdapter<Model, DashboardAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: ItemRecyclerDashboardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Model) {
            binding.apply {
                ivAva.setImageResource(model.img)
                tvName.text = model.name
                tvTransactionCount.text = model.nTransactions.toString()
                tvTransactionsSum.text = model.sum
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Model>() {
        override fun areItemsTheSame(oldItem: Model, newItem: Model) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Model, newItem: Model) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecyclerDashboardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }


}
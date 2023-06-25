package com.example.realestateagency1.ui.fragment.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.realestateagency1.data.local.result.Resource
import com.example.realestateagency1.data.model.Currency
import com.example.realestateagency1.data.model.DataResponseList
import com.example.realestateagency1.databinding.ItemAddBinding

class AddCyrrency(private val resource: Resource<DataResponseList>, private val onClick: (String) -> Unit) :
    ListAdapter<Currency, AddCyrrency.ViewHolder>(DiffCallback()) {


    inner class ViewHolder(private val binding: ItemAddBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(task: Currency) {
            binding.tv.text = task.symbol
            binding.tvId.text = task.id.toString()
            itemView.setOnClickListener {
                onClick(binding.tvId.text.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAddBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(resource.data!!.results[position])
    }

    override fun getItemCount(): Int = resource.data?.results?.size ?: 0

    private class DiffCallback : DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem == newItem
        }
    }
}

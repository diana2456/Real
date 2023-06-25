package com.example.realestateagency1.ui.fragment.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.realestateagency1.data.local.result.Resource
import com.example.realestateagency1.data.model.DataResponse
import com.example.realestateagency1.data.model.Image
import com.example.realestateagency1.data.model.Result
import com.example.realestateagency1.databinding.ItemAddBinding

class AddSer(private val resource: Resource<DataResponse>, private val onClick: (String) -> Unit) :
    ListAdapter<Image, AddSer.ViewHolder>(DiffCallback()) {


    inner class ViewHolder(private val binding: ItemAddBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(task: Result) {
            binding.tv.text = task.title
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
        holder.onBind(resource.data?.results?.get(position)!!)
    }

    override fun getItemCount(): Int = resource.data?.results?.size ?: 0

    private class DiffCallback : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }
}

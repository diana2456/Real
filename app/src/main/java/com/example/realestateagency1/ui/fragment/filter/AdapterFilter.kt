package com.example.realestateagency1.ui.fragment.filter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.realestateagency1.R
import com.example.realestateagency1.data.entity.RealEstate
import com.example.realestateagency1.data.local.result.Resource
import com.example.realestateagency1.data.model.DataResponse
import com.example.realestateagency1.data.model.Image
import com.example.realestateagency1.data.model.ImageResponse
import com.example.realestateagency1.data.model.Result
import com.example.realestateagency1.databinding.ItemAddBinding
import com.example.realestateagency1.databinding.ItemFilBinding
import com.example.realestateagency1.ui.util.loadImage


class AdapterFilter() :
   RecyclerView.Adapter<AdapterFilter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun load(tes:List<RealEstate>){
        list.clear()
        list.addAll(tes)
        notifyDataSetChanged()
    }
    private var isButtonClicked = false
    private val list = arrayListOf<RealEstate>()

    inner class ViewHolder(private val binding:ItemFilBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(task: RealEstate) {
            binding.tvTitle.text = task.tvDil
            binding.ivIcon.setImageResource(task.image)
            binding.cardIv.setOnClickListener {
                    isButtonClicked = !isButtonClicked
                if (isButtonClicked) {
                    binding.cardIv.setCardBackgroundColor(
                        ContextCompat.getColor(
                            binding.cardIv.context,
                            R.color.blue
                        )
                    )
                } else {
                    val defaultColor = ContextCompat.getColor(binding.cardIv.context, R.color.card)
                    binding.cardIv.setBackgroundColor(defaultColor)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFilBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

}
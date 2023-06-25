package com.example.realestateagency1.ui.fragment.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.realestateagency1.data.entity.LoadRel
import com.example.realestateagency1.databinding.ItemLoadingOneBinding

class AdapterOneLoad : ListAdapter<LoadRel,
        AdapterOneLoad.NoteViewHolder>(DiffUtilNoteItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemLoadingOneBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class NoteViewHolder(private val binding: ItemLoadingOneBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(note: LoadRel) {
            binding.tvTitle.text = note.tvTitle
            binding.ivPhoto.setImageResource(note.image)
            binding.tvGrey.text = note.tvRoom
            binding.tvLp.text = note.tvLocation
            binding.tvLoadObav.text = note.tvSan
            binding.tvDil.text = note.tvDil
        }
    }

    private class DiffUtilNoteItemCallback : DiffUtil.ItemCallback<LoadRel>() {
        override fun areItemsTheSame(oldItem: LoadRel, newItem: LoadRel): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: LoadRel, newItem: LoadRel): Boolean {
            return oldItem == newItem
        }
    }
}
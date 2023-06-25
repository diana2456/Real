package com.example.realestateagency1.ui.fragment.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.realestateagency1.R
import com.example.realestateagency1.data.local.result.Resource
import com.example.realestateagency1.data.model.Apartment
import com.example.realestateagency1.data.model.ApartmentListResponse
import com.example.realestateagency1.data.room.FavDB
import com.example.realestateagency1.databinding.ItemTaskBinding
import com.example.realestateagency1.ui.util.loadImage
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.MutableData
import com.google.firebase.database.Transaction

class AdapterOne(private val context: Context,private val apartment: Resource<ApartmentListResponse>,private val onClick: (Apartment) -> Unit) : ListAdapter<Apartment, AdapterOne.ViewHolder>(DiffCallback()) {

    var img = ""
    private lateinit var favDB: FavDB

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        favDB = FavDB(context)
        val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val firstStart = prefs.getBoolean("firstStart", true)
        if (firstStart) {
            createTableOnFirstStart()
        }
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        val coffeeItem = apartment.data?.results?.get(position)
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val viewHolder = holder.binding
            coffeeItem?.let { readCursorData(it, viewHolder, position) }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(apartment.data?.results?.get(position)!!)
    }

    var ilo = ""
    override fun getItemCount(): Int = apartment.data?.results?.size!!

    inner class ViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.heat.setOnClickListener {
                val position = adapterPosition
                val coffeeItem = apartment.data?.results?.get(position)
                coffeeItem?.let {
                    likeClick(it, binding.heat, binding.likeCountTextView)
                }
            }
        }

        fun bind(item: Apartment) {
            with(binding) {
                tvTit.text = item.title
                tvCyr.text = item.currency.symbol
                tvMd.text = item.square
                val pri = item.price
                val formattedNumber = pri.replace(".0+$".toRegex(), "")
                tvSn.text = formattedNumber
                tvMd.text = item.square
                tvRo.text = item.room_count
                tvD.text = item.type.title
                val apartmentImages = item.apartment_images
                if(apartmentImages.isNotEmpty()) {
                    val firstImage = apartmentImages[0]
                    val imageUrl = firstImage.image
                    ivPho.loadImage(imageUrl)
                    Log.i("ololoyu", "Bind:$imageUrl")
                }
                binding.tvLocat.text = item.address
            }
            readCursorData(item, binding, adapterPosition)
            itemView.setOnClickListener {
                onClick(item)
            }
        }
    }

    private fun createTableOnFirstStart() {
        favDB.insertEmpty()

        val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("firstStart", false)
        editor.apply()
    }

    @SuppressLint("Range")
    private fun readCursorData(coffeeItem: Apartment, viewHolder: ItemTaskBinding, position: Int) {
        val cursor = favDB.readAllData(coffeeItem.id)
        val db = favDB.readableDatabase
        try {
            while (cursor.moveToNext()) {
                val itemFavStatus = cursor.getString(cursor.getColumnIndex(FavDB.FAVORITE_STATUS))
                coffeeItem.id = itemFavStatus

                if (itemFavStatus != null && itemFavStatus == "1") {
                    viewHolder.heat.setImageResource(R.drawable.heart_red)
                } else if (itemFavStatus != null && itemFavStatus == "0") {
                    viewHolder.heat.setImageResource(R.drawable.heart)
                }
            }
        } finally {
            cursor.close()
            db.close()
        }
    }

    private fun likeClick(coffeeItem: Apartment, favBtn: ImageView, textLike: TextView) {
        val refLike = FirebaseDatabase.getInstance().reference.child("likes")
        val upvotesRefLike = refLike.child(coffeeItem.id)
        var img = ""

        if(coffeeItem.apartment_images.isNotEmpty()) {
            val firstImage = coffeeItem.apartment_images[0]
            val imageUrl = firstImage.image
            img = imageUrl
            Log.i("ololoyu", "Bind:$imageUrl")
        }
        if (coffeeItem.id == "0") {
            coffeeItem.id = "1"
            favDB.insertIntoTheDatabase(
                coffeeItem.title,
                img,
                coffeeItem.id,
                coffeeItem.id,
                coffeeItem.type.title,
                coffeeItem.room_count,
                coffeeItem.price,
                coffeeItem.address,
                coffeeItem.square
            )
            favBtn.setImageResource(R.drawable.heart_red)
            favBtn.isSelected = true

            upvotesRefLike.runTransaction(object : Transaction.Handler {
                override fun doTransaction(mutableData: MutableData): Transaction.Result {
                    try {
                        val currentValue = mutableData.getValue(Int::class.java)
                        if (currentValue == null) {
                            mutableData.value = 1
                        } else {
                            mutableData.value = currentValue + 1
                            Handler(Looper.getMainLooper()).post {
                                textLike.text = mutableData.value.toString()
                            }
                        }
                    } catch (e: Exception) {
                        throw e
                    }
                    return Transaction.success(mutableData)
                }

                override fun onComplete(databaseError: DatabaseError?, b: Boolean, dataSnapshot: DataSnapshot?) {
                    println("Transaction completed")
                }
            })

        } else if (coffeeItem.id == "1") {
            coffeeItem.id = ""
            favDB.removeFav(coffeeItem.id)
            favBtn.setImageResource(R.drawable.heart)
            favBtn.isSelected = false

            upvotesRefLike.runTransaction(object : Transaction.Handler {
                override fun doTransaction(mutableData: MutableData): Transaction.Result {
                    try {
                        val currentValue = mutableData.getValue(Int::class.java)
                        if (currentValue == null) {
                            mutableData.value = 1
                        } else {
                            mutableData.value = currentValue - 1
                            Handler(Looper.getMainLooper()).post {
                                textLike.text = mutableData.value.toString()
                            }
                        }
                    } catch (e: Exception) {
                        throw e
                    }
                    return Transaction.success(mutableData)
                }

                override fun onComplete(databaseError: DatabaseError?, b: Boolean, dataSnapshot: DataSnapshot?) {
                    println("Transaction completed")
                }
            })
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Apartment>() {
        override fun areItemsTheSame(oldItem: Apartment, newItem: Apartment): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Apartment, newItem: Apartment): Boolean {
            return oldItem == newItem
        }
    }
}

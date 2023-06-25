@file:Suppress("DEPRECATION")

package com.example.realestateagency1.ui.fragment.home.real_estate.view_pager

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.realestateagency1.data.local.result.Resource
import com.example.realestateagency1.data.model.ApartmentImage
import com.example.realestateagency1.data.model.ImageApartmentListResponse

class AdapterViewPager(fragment: FragmentActivity,private val listImage: Resource<ImageApartmentListResponse>): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int =  listImage.data?.results?.size ?: 0

    fun getTask(pos:Int): ApartmentImage {
        return  listImage.data?.results?.get(pos)!!
    }
    override fun createFragment(position: Int): Fragment {
        val img = getTask(position)
            val data = bundleOf(ON_BOR to img)
            val fragment = OnBoardVpFragment()
            fragment.arguments = data
            return fragment

    }
    companion object{
        const val ON_BOR = "onBoard"
    }
}
package com.example.realestateagency1.ui.fragment.home.view_pager

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.example.realestateagency1.R
import com.example.realestateagency1.databinding.FragmentOnBoardBinding
import com.example.realestateagency1.ui.fragment.home.real_estate.view_pager.Model


class AdapterViewPager(fragment: FragmentActivity): FragmentStateAdapter(fragment) {


    private val listBoarding = arrayOf(
        Model(R.drawable.olp),
        Model(R.drawable.pager_three),
        Model(R.drawable.pader_four),
        Model(R.drawable.per_one),)


    override fun getItemCount(): Int = listBoarding.size

    override fun createFragment(position: Int): Fragment {
        val data = bundleOf(ON_BOARD to listBoarding[position])
        val fragment = OnBoardFragment()
        fragment.arguments = data
        return fragment
    }

    companion object{
        const val ON_BOARD = "onBoard"
    }
}
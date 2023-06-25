package com.example.realestateagency1.ui.fragment.home.real_estate.view_pager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.realestateagency1.R
import com.example.realestateagency1.data.model.ApartmentImage
import com.example.realestateagency1.databinding.FragmentOnBoardVpBinding
import com.example.realestateagency1.ui.fragment.home.real_estate.view_pager.AdapterViewPager.Companion.ON_BOR
import com.example.realestateagency1.ui.util.loadImage

class OnBoardVpFragment : Fragment() {


    private lateinit var binding: FragmentOnBoardVpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentOnBoardVpBinding.inflate(inflater, container, false)
        onBoard()
        return binding.root
    }

    private fun onBoard() {
        arguments.let {val data = it?.getSerializable(ON_BOR) as ApartmentImage
            binding.plo.loadImage(data.image)
            Log.i("iop", "onBoard:${data.image}")}

        binding.plo.setOnClickListener {
            findNavController().navigate(R.id.photoFragment)
        }
    }

}
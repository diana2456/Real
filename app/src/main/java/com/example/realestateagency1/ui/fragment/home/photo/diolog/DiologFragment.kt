package com.example.realestateagency1.ui.fragment.home.photo.diolog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.realestateagency1.R
import com.example.realestateagency1.databinding.DiologPhotoBinding

class DiologFragment : Fragment() {

    private lateinit var binding : DiologPhotoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = DiologPhotoBinding.inflate(inflater,container,false)
        return binding.root
    }
}
package com.example.realestateagency1.ui.fragment.home.photo

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.realestateagency1.ui.fragment.home.real_estate.view_pager.AdapterViewPager
import com.example.realestateagency1.R
import com.example.realestateagency1.data.local.result.Status
import com.example.realestateagency1.databinding.FragmentPhotoBinding
import com.google.android.material.button.MaterialButton


class PhotoFragment : Fragment() {

    private lateinit var binding : FragmentPhotoBinding

    private val args by navArgs<PhotoFragmentArgs>()

    private val viewModel : PhotoViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPhotoBinding.inflate(inflater,container,false)
        initView()
        onViewModel()
        return binding.root
    }

    private fun initView() {
        binding.ivBlack.setOnClickListener{
            findNavController().navigate(R.id.realAgencyFragment)
        }
        binding.alertTv.setOnClickListener {
            val view = LayoutInflater.from(requireContext()).inflate(R.layout.diolog_photo, null)
            val builder = AlertDialog.Builder(requireContext())
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
            dialog.window?.setGravity(Gravity.BOTTOM)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(true)
            val btn = view.findViewById<MaterialButton>(R.id.btn_tri)
            btn.setOnClickListener {
                dialog.dismiss()
            }
        }
    }

    private fun onViewModel(){


        viewModel.getImage(5, args.ara.id).observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> {
                    viewModel.loading.postValue(false)
                    val adapterViewPager = AdapterViewPager(requireActivity(), it)
                    binding.vpv.adapter = adapterViewPager
                    binding.dotsIndicator.attachTo(binding.vpv)
                }
                Status.ERROR -> {
                    viewModel.loading.postValue(true)
                    Log.i("olerrt", "initViewModel:${it.message}")
                }
                Status.LOADING -> viewModel.loading.postValue(true)
            }
        }
    }
}
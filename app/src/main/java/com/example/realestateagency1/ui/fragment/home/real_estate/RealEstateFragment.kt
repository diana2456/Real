package com.example.realestateagency1.ui.fragment.home.real_estate

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.realestateagency1.R
import com.example.realestateagency1.data.local.result.Status
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.realestateagency1.databinding.FragmentRealEstateBinding
import com.example.realestateagency1.ui.fragment.home.real_estate.view_pager.AdapterViewPager
import com.google.android.material.button.MaterialButton

class RealEstateFragment : Fragment() {

    private lateinit var binding: FragmentRealEstateBinding
    private val viewModel : RealEstateViewModel by viewModel()
    private val args by navArgs<RealEstateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRealEstateBinding.inflate(
            inflater,
            container,
            false
        )
        binding.shimmer.startShimmer()
        initView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewModel()
    }

    @SuppressLint("SetTextI18n")
    private fun onViewModel(){
        viewModel.loading.observe(requireActivity()){
            binding.shimmer.isVisible = it
        }

        viewModel.getImage(5,args.ara.id).observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> {
                    viewModel.loading.postValue(false)
                    binding.item.tvTitle.setText(args.ara.title)
                    binding.item.tvDesc.text = args.ara.description
                    binding.item.tvDil.setText(args.ara.type.title)
                    binding.item.tvLocation.text = args.ara.address
                    binding.item.tvRoom.text = args.ara.room_count
                    binding.item.tvKm.text = args.ara.square
                    binding.item.document.text = "Документы : ${args.ara.document}"
                    binding.item.floor.text = "Этажность : ${args.ara.floor.title}"
                    binding.item.comunikation.text = "Коммуникации : ${args.ara.communications}"
                    binding.item.series.text = "Состояние :${args.ara.series.title}"
                    binding.item.tvSan.text = args.ara.price
                    binding.item.tvName.text = args.ara.author.first_name
                    val adapterViewPager = AdapterViewPager(requireActivity(), it)
                    binding.item.vpv.adapter = adapterViewPager
                    binding.item.dotsIndicator.attachTo(binding.item.vpv)
                }
                Status.ERROR -> {
                    viewModel.loading.postValue(true)
                    Log.i("olerrt", "initViewModel:${it.message}")
                }
                Status.LOADING -> viewModel.loading.postValue(true)
            }
        }
    }


    private fun initView() {
        binding.item.bo.setOnClickListener {
            findNavController().navigate(R.id.navigation_home)
        }
        binding.item.tvDil.setOnClickListener {
            val view = LayoutInflater.from(requireContext()).inflate(R.layout.diolog_photo, null)
            val builder = AlertDialog.Builder(requireContext())
            builder . setView (view)
            val dialog = builder.create()
            dialog . show ()
            dialog.window?.setGravity(Gravity.BOTTOM)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog . setCancelable (true)
            val btn = view.findViewById<MaterialButton>(R.id.btn_tri)
            btn.setOnClickListener {
                dialog.dismiss()
            }
        }
    }
}
package com.example.realestateagency1.ui.fragment.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.realestateagency1.ui.fragment.home.AdapterRealEstate
import com.example.realestateagency1.ui.fragment.home.AdapterTwoLoad
import com.example.realestateagency1.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.realestateagency1.data.entity.LoadRel
import com.example.realestateagency1.data.local.result.Status
import com.example.realestateagency1.data.model.Apartment
import com.example.realestateagency1.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var binding : FragmentDashboardBinding
    private val listLoad: ArrayList<LoadRel> = arrayListOf()
    private val viewModel : DashboardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater,container,false)
        binding.shimmer.startShimmer()
        listLoad.add(LoadRel(
            image = R.drawable.screensaver,
            tvDil = "",
            tvTitle = "",
            tvSan = "",
            tvKm = "",
            tvLocation = "",
            tvRoom = "",
            id = ""))
        listLoad.add(LoadRel(
            image = R.drawable.screensaver,
            tvDil = "",
            tvTitle = "",
            tvSan = "",
            tvKm = "",
            tvLocation = "",
            tvRoom = "",
            id = ""
        ))
        listLoad.add(LoadRel(
            image = R.drawable.screensaver,
            tvDil = "",
            tvTitle = "",
            tvSan = "",
            tvKm = "",
            tvLocation = "",
            tvRoom = "",
            id = ""
        ))
        listLoad.add(LoadRel(
            image = R.drawable.screensaver,
            tvDil = "",
            tvTitle = "",
            tvSan = "",
            tvKm = "",
            tvLocation = "",
            tvRoom = "",
            id = ""
        ))
        val adapterLoad = AdapterTwoLoad()
        adapterLoad.submitList(listLoad)
        binding.load.rvRecom.adapter = adapterLoad

        recycler()
        onViewModel()
        initViews()
        return binding.root
    }


    fun recycler(){
        val SCROLL_THRESHOLD = 200

        binding.itemD.rvRecom.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItemPosition <= SCROLL_THRESHOLD) {
                    binding.itemD.tvSearch.ivSearch.visibility = View.VISIBLE
                } else {
                    binding.itemD.tvSearch.ivSearch.visibility = View.GONE
                }
            }
        })
    }

    private fun onViewModel() {
        viewModel.loading.observe(requireActivity()) {
            binding.shimmer.isVisible = it
        }

        viewModel.getApartment().observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> {
                    viewModel.loading.postValue(false)
                    binding.itemD.rvRecom.adapter =
                        AdapterRealEstate(requireActivity(),it, this::onClick)
                }
                Status.ERROR -> {
                    viewModel.loading.postValue(true)
                    Log.i("ololo", "initViewModel:${it.message}")
                }
                Status.LOADING -> viewModel.loading.postValue(true)
            }
        }
    }
    private fun initViews(){
        var img = ""
        binding.itemD.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                binding.itemD.search.clearFocus()
                img = query
                viewModel.searFilter(img).observe(requireActivity()){ data ->
                    when(data.status) {
                        Status.SUCCESS -> {
                            viewModel.loading.postValue(false)
                        }
                        Status.ERROR -> {
                            viewModel.loading.postValue(true)
                        }
                        Status.LOADING -> viewModel.loading.postValue(true)
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                img = newText
                viewModel.searFilter(img).observe(requireActivity()){ data ->
                    when(data.status) {
                        Status.SUCCESS -> {
                            viewModel.loading.postValue(false)
                            binding.itemD.llVv.isVisible = false
                            Log.i("pku", "onViewModel:$data")
                            binding.itemD.rty.isVisible = true
                            binding.itemD.tvb.text = img
                            if (binding.itemD.dfg.text.isEmpty()){
                                binding.itemD.df.isVisible = false
                            }
                            if (binding.itemD.ff.text.isEmpty()){
                                binding.itemD.llThree.isVisible = false
                            }
                            if (binding.itemD.fhh.text.isEmpty()){
                                binding.itemD.llFour.isVisible = false
                            }
                            if (binding.itemD.sdrt.text.isEmpty()){
                                binding.itemD.llFife.isVisible = false
                            }
                            if (binding.itemD.werf.text.isEmpty()){
                                binding.itemD.llSix.isVisible = false
                            }
                            if (binding.itemD.sfgv.text.isEmpty()){
                                binding.itemD.llSeven.isVisible = false
                            }
                            if (binding.itemD.jjj.text.isEmpty()){
                                binding.itemD.llEiht.isVisible = false
                            }
                            binding.itemD.g.isVisible = true
                            binding.itemD.g.setOnClickListener {
                                binding.itemD.rty.isVisible = false
                                binding.itemD.g.isVisible = false
                                binding.itemD.llVv.isVisible = true
                            }
                            binding.itemD.tvNumber.text = "${data.data?.count} объявления"
                            binding.itemD.rvRecom.adapter = AdapterRealEstate(requireActivity(),data,this::onClick)
                        }
                        Status.ERROR -> {
                            viewModel.loading.postValue(true)
                        }
                        Status.LOADING -> viewModel.loading.postValue(true)
                    }
                }
                return false
            }

            private fun onClick(pos: Apartment){}
            private fun onClicdk(pos:Int){}
        })
    }
    private fun onClick(pos:Apartment){}
}


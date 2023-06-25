package com.example.realestateagency1.ui.fragment.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.realestateagency1.data.local.result.Resource
import com.example.realestateagency1.data.model.ApartmentListResponse
import com.example.realestateagency1.data.model.DataReonse
import com.example.realestateagency1.data.model.DataResponse
import com.example.realestateagency1.data.model.ImageResponse
import com.example.realestateagency1.data.repository.Repository

class FilterViewModel(private val repository: Repository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getApartment(): LiveData<Resource<ApartmentListResponse>> {
        return repository.setApartment()
    }

    fun getBanner(): LiveData<Resource<ImageResponse>>{
        return repository.getBanner()
    }

    fun searchFil(title: String): LiveData<Resource<ApartmentListResponse>> {
        return repository.searFilter(title)
    }

    fun search(region: String,room:String): LiveData<Resource<ApartmentListResponse>>{
        return repository.search(region, room)
    }

    fun getType(): LiveData<Resource<DataResponse>> {
        return repository.getType()
    }

    fun getRegion(): LiveData<Resource<DataReonse>> {
        return repository.getRegion()
    }
}
package com.example.realestateagency1.ui.fragment.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.realestateagency1.data.local.result.Resource
import com.example.realestateagency1.data.model.ApartmentListResponse
import com.example.realestateagency1.data.model.ApartmentTypeResponse
import com.example.realestateagency1.data.repository.Repository

class DashboardViewModel (private val repository: Repository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun searFilter(title: String): LiveData<Resource<ApartmentListResponse>> {
        return repository.searFilter(title)
    }

    fun getApartmentType(): LiveData<Resource<ApartmentTypeResponse>> {
        return repository.getApartmentType()
    }

    fun getApartment(): LiveData<Resource<ApartmentListResponse>> {
        return repository.setApartment()
    }
}
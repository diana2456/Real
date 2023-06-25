package com.example.realestateagency1.ui.fragment.all

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.realestateagency1.data.local.result.Resource
import com.example.realestateagency1.data.model.ApartmentListResponse
import com.example.realestateagency1.data.repository.Repository

class AllViewModel (private val repository: Repository): ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getApartment(): LiveData<Resource<ApartmentListResponse>> {
        return repository.setApartment()
    }
}
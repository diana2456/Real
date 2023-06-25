package com.example.realestateagency1.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.realestateagency1.data.local.result.Resource
import com.example.realestateagency1.data.model.AddFavoriteRequest
import com.example.realestateagency1.data.model.ApartmentListResponse
import com.example.realestateagency1.data.model.Favorite
import com.example.realestateagency1.data.model.LoginResponse
import com.example.realestateagency1.data.model.TokenObtainPair
import com.example.realestateagency1.data.repository.Repository

class HomeViewModel (private val repository: Repository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getApartment(): LiveData<Resource<ApartmentListResponse>> {
        return repository.setApartment()
    }


    fun search(region: String,room:String): LiveData<Resource<ApartmentListResponse>>{
        return repository.search( region, room)
    }
}
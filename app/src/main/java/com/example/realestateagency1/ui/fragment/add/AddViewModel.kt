package com.example.realestateagency1.ui.fragment.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.realestateagency1.data.local.result.Resource
import com.example.realestateagency1.data.model.ApartmentCreate
import com.example.realestateagency1.data.model.ApartmentImage
import com.example.realestateagency1.data.model.DataReonse
import com.example.realestateagency1.data.model.DataResponse
import com.example.realestateagency1.data.model.DataResponseList
import com.example.realestateagency1.data.model.DocumentResponse
import com.example.realestateagency1.data.model.Floor
import com.example.realestateagency1.data.model.LoginResponse
import com.example.realestateagency1.data.model.Result
import com.example.realestateagency1.data.model.Series
import com.example.realestateagency1.data.model.TokenObtainPair
import com.example.realestateagency1.data.repository.Repository
import okhttp3.MultipartBody


class AddViewModel (private val repository: Repository):ViewModel() {

    val loading = MutableLiveData<Boolean>()

      fun  addApartment(token:String,data: ApartmentCreate) : LiveData<Resource<ApartmentCreate>>{
        return repository.addApartment(token,data)
    }

      fun setFloor(token: String, data: String): LiveData<Resource<Floor>>{
        return repository.setFloor(token, data)
    }

      fun setImage(token: String, imageFile: MultipartBody.Part, apartmentId: Int): LiveData<Resource<ApartmentImage>>{
        return repository.setImage(token, imageFile, apartmentId)
    }


    fun getSeries():LiveData<Resource<DataResponse>>{
        return repository.getSeries()
    }

    fun getCyrency():LiveData<Resource<DataResponseList>>{
        return repository.getCurrency()
    }

    fun  addCyrency(token:String,symbol:String,name: String): LiveData<Resource<Result>> {
        return  repository.addCurrency(token,symbol,name)
    }

    fun getType(): LiveData<Resource<DataResponse>> {
        return repository.getType()
    }

    fun getRegion(): LiveData<Resource<DataReonse>> {
        return repository.getRegion()
    }

    fun getDocument(): LiveData<Resource<DataResponse>> {
        return repository.getDocument()
    }

    fun addDocument(token:String,name:String): LiveData<Resource<DocumentResponse>> {
        return repository.addDocument(token, name)
    }

    fun addType(token:String,name:String): LiveData<Resource<Result>> {
        return repository.addType(token, name)
    }

    fun addRegion(token:String,name:String): LiveData<Resource<Result>> {
        return repository.addRegion(token, name)
    }

    fun addSeries(token:String,name:String): LiveData<Resource<Series>> {
        return repository.addSeries(token,name)
    }

    fun getFloor(): LiveData<Resource<DataResponse>> {
        return repository.getFloor()
    }

    fun addTokenLogin(data: TokenObtainPair) : LiveData<Resource<LoginResponse>> {
        return repository.addTokenLogin(data)
    }
}
package com.example.realestateagency1.data.repository

import androidx.lifecycle.liveData
import com.example.realestateagency1.data.local.result.Resource
import com.example.realestateagency1.data.model.AddFavoriteRequest
import com.example.realestateagency1.data.model.Ads
import com.example.realestateagency1.data.model.ApartmentCreate
import com.example.realestateagency1.data.model.TokenObtainPair
import com.example.realestateagency1.data.model.addUser
import kotlinx.coroutines.Dispatchers
import okhttp3.MultipartBody
import okhttp3.RequestBody


class Repository (private val remoteDataSource: RemoteDataSource) {

    fun searFilter(title: String) = liveData(Dispatchers.IO){
        emit(Resource.loading())
       val filter =remoteDataSource.searFilter(title)
        emit(filter)
    }

    fun  searchUser(name:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val searchUser = remoteDataSource.searchUser(name)
        emit(searchUser)
    }

    fun  getImage(limit: Int, idApartment: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val getImage = remoteDataSource.getImage(limit,idApartment)
        emit(getImage)
    }

    fun getApartmentType() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val getApartmentType = remoteDataSource.getApartmentType()
        emit(getApartmentType)
    }

    fun setApartment() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val apartment = remoteDataSource.setApartment()
        emit(apartment)
    }

    fun addApartment(token:String,data: ApartmentCreate) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val addApartment = remoteDataSource.addApartment(token,data)
        emit(addApartment)
    }

    fun  setFloor(token: String,data: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val floor = remoteDataSource.setFloor(token,data)
        emit(floor)
    }

    fun setImage(token:String, imageFile: MultipartBody.Part, apartmentId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val image = remoteDataSource.setImage(token,imageFile,apartmentId)
        emit(image)
    }

    fun addTokenLogin(data: TokenObtainPair) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val addTokenLogin = remoteDataSource.addTokenLogin(data)
        emit(addTokenLogin)
    }

    fun addUser(data: addUser) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val addUser = remoteDataSource.addUser(data)
        emit(addUser)
    }

    fun getBanner() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val banner = remoteDataSource.addBanner()
        emit(banner)
    }

    fun addAds(data: Ads) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val addAds = remoteDataSource.addAds(data)
        emit(addAds)
    }

    fun getType() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val type = remoteDataSource.getType()
        emit(type)
    }

    fun getRegion() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val region = remoteDataSource.getRegion()
        emit(region)
    }

    fun getSeries() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val series = remoteDataSource.getSeries()
        emit(series)
    }

    fun getFloor() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val floor = remoteDataSource.getFloor()
        emit(floor)
    }

    fun getDocument() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val document = remoteDataSource.getDocument()
        emit(document)
    }

    fun addDocument(token: String,name: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val addDocument = remoteDataSource.addDocument(token, name)
        emit(addDocument)
    }

    fun addRegion(token: String,name: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val addRegion = remoteDataSource.addRegion(token, name)
        emit(addRegion)
    }

    fun addSeries(token: String,name: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val addSeries = remoteDataSource.addSeries(token, name)
        emit(addSeries)
    }

    fun addType(token: String,name: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val addType = remoteDataSource.addType(token, name)
        emit(addType)
    }

    fun addCurrency(token: String,symbol:String,name: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val cyrrency = remoteDataSource.addCurrency(token,symbol, name)
        emit(cyrrency)
    }

    fun getCurrency() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val cyrrency = remoteDataSource.getCurrency()
        emit(cyrrency)
    }

    fun search(region: String,room:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val sear = remoteDataSource.search( region, room)
        emit(sear)
    }
}
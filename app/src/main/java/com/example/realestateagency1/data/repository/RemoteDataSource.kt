package com.example.realestateagency1.data.repository


import com.example.realestateagency1.data.local.BaseDataSource
import com.example.realestateagency1.data.local.RealEstateAPI
import com.example.realestateagency1.data.model.AddFavoriteRequest
import com.example.realestateagency1.data.model.Ads
import com.example.realestateagency1.data.model.ApartmentCreate
import com.example.realestateagency1.data.model.TokenObtainPair
import com.example.realestateagency1.data.model.addUser
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.dsl.module

val remoteDataSourceModule = module {
    factory { RemoteDataSource(get()) }
}

@Suppress("unused")
class RemoteDataSource (private val realEstateAPI: RealEstateAPI):
    BaseDataSource(){

    suspend fun searFilter(title: String,) = getResult {
        realEstateAPI.getApartments(title)
    }

    suspend fun searchUser(name:String) = getResult {
        realEstateAPI.searchUsers(name)
    }
    suspend fun getImage(limit: Int, idApartment: String) = getResult {
        realEstateAPI.getApartmentImages(limit,idApartment)
    }

    suspend fun getApartmentType() = getResult {
        realEstateAPI.getApartmentType()
    }

    suspend fun setApartment() = getResult{
        realEstateAPI.setApartments()
    }

    suspend fun addApartment(token:String,data: ApartmentCreate) = getResult{
        realEstateAPI.createApartment(token,data)
    }

    suspend fun setFloor(token:String,data:String) = getResult{
        realEstateAPI.createFloorApartment(token,data)
    }

    suspend fun setImage(token:String, imageFile: MultipartBody.Part, apartmentId: Int)= getResult{
        realEstateAPI.createImageApartment(token,imageFile,apartmentId)
    }


    suspend fun addSeries(token:String,data:String) = getResult{
        realEstateAPI.createSeriesApartment(token,data)
    }

    suspend fun addTokenLogin(data: TokenObtainPair) = getResult{
        realEstateAPI.createTokenLogin(data)
    }

    suspend fun addUser(data: addUser) = getResult{
        realEstateAPI.createUser(data)
    }

    suspend fun addAds(data: Ads) = getResult {
        realEstateAPI.addAds(data)
    }

    suspend fun addBanner() = getResult {
        realEstateAPI.getBanner()
    }

    suspend fun getType() = getResult {
        realEstateAPI.getType()
    }

    suspend fun getRegion() = getResult {
        realEstateAPI.getRegion()
    }

    suspend fun getSeries() = getResult {
        realEstateAPI.getSeries()
    }

    suspend fun getFloor() = getResult {
        realEstateAPI.getFloor()
    }

    suspend fun getDocument() = getResult{
        realEstateAPI.getDocument()
    }

    suspend fun addDocument(token: String,name: String) = getResult{
        realEstateAPI.addDocument(token,name)
    }

    suspend fun  addRegion(token: String,name: String) = getResult {
        realEstateAPI.addRegion(token, name)
    }

    suspend fun addCurrency(token: String,symbol: String,name: String) = getResult {
        realEstateAPI.addCurrency(token,symbol,name)
    }

    suspend fun getCurrency() = getResult {
        realEstateAPI.getCurrency()
    }

    suspend fun addType(token: String,name: String) = getResult {
        realEstateAPI.addType(token, name)
    }

    suspend fun search(region: String,room:String) = getResult {
        realEstateAPI.search(region,room)
    }
}
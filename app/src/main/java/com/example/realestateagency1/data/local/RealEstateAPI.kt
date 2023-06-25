package com.example.realestateagency1.data.local

import com.example.realestateagency1.data.model.AddFavoriteRequest
import com.example.realestateagency1.data.model.Ads
import com.example.realestateagency1.data.model.AdsAp
import com.example.realestateagency1.data.model.Apartment
import com.example.realestateagency1.data.model.ApartmentCreate
import com.example.realestateagency1.data.model.ApartmentImage
import com.example.realestateagency1.data.model.ApartmentListResponse
import com.example.realestateagency1.data.model.ApartmentTypeResponse
import com.example.realestateagency1.data.model.DataReonse
import com.example.realestateagency1.data.model.DataResponse
import com.example.realestateagency1.data.model.DataResponseList
import com.example.realestateagency1.data.model.DocumentResponse
import com.example.realestateagency1.data.model.Favorite
import com.example.realestateagency1.data.model.Floor
import com.example.realestateagency1.data.model.ImageApartmentListResponse
import com.example.realestateagency1.data.model.ImageResponse
import com.example.realestateagency1.data.model.LoginResponse
import com.example.realestateagency1.data.model.Result
import com.example.realestateagency1.data.model.Series
import com.example.realestateagency1.data.model.TokenObtainPair
import com.example.realestateagency1.data.model.UserResponse
import com.example.realestateagency1.data.model.addUser
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface RealEstateAPI {

           @GET("apartment/")
         suspend fun getApartments(
               @Query("search") title: String,
            ): Response<ApartmentListResponse>


         @GET("apartment/")
         suspend fun search(
             @Query("region")region: String,
             @Query("room_count") room:String
         ):Response<ApartmentListResponse>

        @GET("apartment/")
        suspend fun setApartments(
        ): Response<ApartmentListResponse>

        @POST("apartment/")
       suspend fun createApartment(
            @Header("Authorization") token: String,
            @Body data: ApartmentCreate
        ): Response<ApartmentCreate>

        @POST("floor/apartment/")
       suspend fun createFloorApartment(
            @Header("Authorization") token: String,
            @Body data: String
        ): Response<Floor>


        @Multipart
        @POST("image/apartments/")
        suspend fun createImageApartment(
            @Header("Authorization") token: String,
            @Part image : MultipartBody.Part,
            @Part("apartment") apartmentId: Int
        ): Response<ApartmentImage>

        @GET("image/apartments/")
        suspend fun getApartmentImages(
            @Query("limit") limit: Int,
            @Query("offset") offset: String
        ): Response<ImageApartmentListResponse>

        @POST("series/apartment/")
       suspend fun createSeriesApartment(
            @Header("Authorization") token: String,
            @Body series: String
        ): Response<Series>


        @POST("token/login/")
       suspend fun createTokenLogin(
            @Body credentials: TokenObtainPair
        ): Response<LoginResponse>

       @GET("types/apartments/")
       suspend fun getApartmentType():
               Response<ApartmentTypeResponse>

        @GET("users/")
        suspend fun searchUsers(
            @Query("login") query: String
        ): Response<UserResponse>

        @POST("users/")
       suspend fun createUser(
            @Body user: addUser
        ): Response<addUser>

       @POST("ads/")
       suspend fun addAds(
           @Body ads: Ads
       ): Response<AdsAp>

       @GET("banner/")
       suspend fun getBanner():Response<ImageResponse>

       @POST("document/apartment/")
       suspend fun addDocument(
           @Header("Authorization")token: String,
           @Body title: String
       ):Response<DocumentResponse>

       @GET("document/apartment/")
       suspend fun getDocument():Response<DataResponse>

       @GET("floor/apartment/")
       suspend fun getFloor():Response<DataResponse>

       @GET("types/apartments/")
       suspend fun getType():Response<DataResponse>

       @GET("series/apartment/")
       suspend fun getSeries():Response<DataResponse>

       @GET("region/")
       suspend fun getRegion():Response<DataReonse>

       @GET("currency/")
       suspend fun getCurrency():Response<DataResponseList>

       @POST("currency/")
       suspend fun addCurrency(
           @Header("Authorization") token: String,
           @Body symbol:String,name: String
       ):Response<Result>

       @POST("region/")
       suspend fun addRegion(
           @Header("Authorization") token: String,
           @Body name:String
       ):Response<Result>

        @POST("types/apartments/")
        suspend fun addType(
            @Header("Authorization")token: String,
            @Body name: String
        ):Response<Result>
}
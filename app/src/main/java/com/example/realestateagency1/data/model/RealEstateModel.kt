package com.example.realestateagency1.data.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

data class ApartmentListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Apartment>
)

@SuppressLint("ParcelCreator")
data class Apartment(
    var id: String,
    val title: String,
    val square: String,
    val address: String,
    val communications: String,
    val description: String,
    val best: Boolean,
    val price: String,
    val room_count: String,
    val lat: String,
    val lng: String,
    val currency: Currency,
    val created_at: String,
    val type: ApartmentType,
    val floor: Floor,
    val document: Document,
    val series: Series,
    val region: Region,
    val apartment_images: List<ApartmentImage>,
    val author: User
): Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }
}

data class ApartmentTypeResponse(
    val id: Int,
    val title: String,
    val created_at: String
)

data class ApartmentTypeListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ApartmentType>
)

data class ApartmentType(
    val title: String
)

data class Floor(
    val title: String
)

data class FloorApartmentListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Floor>
)

data class Document(
    val id: Int,
    val title: String,
    val created_at: String
)

data class Series(
    val id: Int,
    val title: String,
    val created_at: String
)

data class SeriesApartmentListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Series>
)

data class SeriesApartmentUpdateRequest(
    val title: String
)

data class Region(
    val id: Int,
    val name: String,
    val created_at: String
)

data class Currency(
    val id: Int,
    val symbol: String,
    val name: String,
    val created_at: String
)

data class RegionListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Region>
)

data class RegionUpdateRequest(
    val name: String
)


data class ApartmentImage(
    val id: Int,
    val image: String,
    val created_at: String,
    val apartment: Int
) :java.io.Serializable

data class ImageApartmentListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ApartmentImage>
)

data class Ads(
    val name: String,
    val phone: String
)

data class addUser(
    var login: String,
    var password: String
)
data class User(
    val id: Int,
    var is_superuser: Boolean,
    var login: String,
    var first_name: String,
    var last_name: String,
    var middle_name: String?,
    var password: String,
    val created_at: String,
    var is_staff: Boolean,
    var is_active: Boolean
)


data class UserResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<User>
)

data class ApartmentCreate(
    val title: String?,
    val square: String?,
    val address: String?,
    val communications: String?,
    val description: String?,
    val best: Boolean?,
    val price: String?,
    val room_count: String?,
    val lat: String?,
    val lng: String?,
    val author: Int,
    val type: Int,
    val floor: Int,
    val document: Int,
    val series: Int,
    val region: Int,
    val currency: Int,
)

data class Service(
    val id: Int,
    val title: String,
    val description: String,
    val created_at: String
)

data class ServiceListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Service>
)

data class ServicePartialUpdateRequest(
    val title: String?,
    val description: String?
)

data class TokenObtainPair(
    var login: String,
    var password: String
)

data class LoginResponse(
    val refresh: String,
    val access: String
)

data class ImageResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Image>
)

data class Image(
    val id: Int,
    val title: String,
    val url: String,
    val description: String,
    val created_at: String
)

data class DocumentResponse(
    val id: Int,
    val title: String,
    val created_at: String
)

data class Result(
    val id: Int,
    val title: String,
    val created_at: String
)

data class Resut(
    val id: Int,
    val title: String,
    val created_at: String
)

data class AdsAp(
    val id: Int,
    val name: String,
    val phone: String,
     val created_at: String
)

/*{
  "id": 3,
  "name": "yhj",
  "phone": "2345677",
  "created_at": "2023-06-24 21:30:14"
}*/
data class DataResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Result>
)

data class DataReonse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Region>
)

data class DataResponseList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Currency>
)

data class Favorite(
    val id: Int,
    val created_at: String,
    val user: Int,
    val apartment: Int
)

data class AddFavoriteRequest(
    val user: Int,
    val apartment: Int
)


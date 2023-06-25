package com.example.realestateagency1.ui.fragment.about_us

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.realestateagency1.data.model.Ads
import com.example.realestateagency1.data.local.result.Resource
import com.example.realestateagency1.data.model.AdsAp
import com.example.realestateagency1.data.repository.Repository


class AboutUsViewModel(private val repository: Repository): ViewModel() {

    fun addAds(data: Ads): LiveData<Resource<AdsAp>> {
        return repository.addAds(data)
    }

}
package com.example.realestateagency1.ui.fragment.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.realestateagency1.data.local.result.Resource
import com.example.realestateagency1.data.model.addUser
import com.example.realestateagency1.data.repository.Repository

class RegistrationViewModel (private val repository: Repository): ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun addUser(data: addUser): LiveData<Resource<addUser>>{
        return repository.addUser(data)
    }

}
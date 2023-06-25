package com.example.realestateagency1.data.di

import com.example.realestateagency1.data.local.networkModule
import com.example.realestateagency1.data.repository.remoteDataSourceModule

val koinModules = listOf(
    repoModules,
    viewModules,
    networkModule,
    remoteDataSourceModule
)
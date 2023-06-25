package com.example.realestateagency1.data.di

import com.example.realestateagency1.data.repository.Repository
import org.koin.dsl.module


val repoModules = module {
    single { Repository(get()) }
}
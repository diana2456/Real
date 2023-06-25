package com.example.realestateagency1.data.di
import com.example.realestateagency1.ui.fragment.about_us.AboutUsViewModel
import com.example.realestateagency1.ui.fragment.add.AddViewModel
import com.example.realestateagency1.ui.fragment.all.AllViewModel
import com.example.realestateagency1.ui.fragment.dashboard.DashboardViewModel
import com.example.realestateagency1.ui.fragment.filter.FilterViewModel
import com.example.realestateagency1.ui.fragment.home.HomeViewModel
import com.example.realestateagency1.ui.fragment.home.photo.PhotoViewModel
import com.example.realestateagency1.ui.fragment.home.real_estate.RealEstateViewModel
import com.example.realestateagency1.ui.fragment.login.LoginViewModel
import com.example.realestateagency1.ui.fragment.notifications.NotificationsViewModel
import com.example.realestateagency1.ui.fragment.registration.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModules = module {
    viewModel { AllViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { AddViewModel(get()) }
    viewModel { DashboardViewModel(get()) }
    viewModel { PhotoViewModel(get()) }
     viewModel { LoginViewModel(get()) }
    viewModel { RegistrationViewModel(get()) }
    viewModel { AboutUsViewModel(get()) }
    viewModel { FilterViewModel(get()) }
    viewModel { RealEstateViewModel(get()) }
    viewModel { NotificationsViewModel(get()) }
}

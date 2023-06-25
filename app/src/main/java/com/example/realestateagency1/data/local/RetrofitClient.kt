package com.example.realestateagency1.data.local

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

    const val BASE_URL = "https://vm4506017.43ssd.had.wf/api/"
    val networkModule = module {
        single { ProvideRetrofit(get()) }
        factory { provideOkHttpClient() }
        factory { provideApi(get()) }
    }

    fun ProvideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
    fun provideOkHttpClient():OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }
    fun provideApi(retrofit:Retrofit) = retrofit.create(RealEstateAPI::class.java)

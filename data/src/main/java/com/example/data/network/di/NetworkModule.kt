package com.example.data.network.di

import com.example.data.network.ApiConfig
import com.example.data.network.repository.SearchRepositoryImp
import com.example.domain.repository.SearchRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {

    single { providerRetrofit() }
    single { providerApi(get()) }

    single<SearchRepository> { SearchRepositoryImp(get()) }
}

fun providerApi(retrofit: Retrofit): ApiConfig {
    return retrofit.create(ApiConfig::class.java)
}

fun providerRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://itunes.apple.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


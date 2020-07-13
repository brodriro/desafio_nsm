package com.example.data.preference.di


import com.example.data.preference.UserPreference
import com.example.data.preference.manager.PreferencesManager
import com.example.domain.repository.SearchRepositoryPreference
import com.google.gson.Gson
import org.koin.dsl.module

val preferenceModule = module {
    single { PreferencesManager(get()) }
    single { providerGson() }
    single<SearchRepositoryPreference> { UserPreference(get(), get()) }
}

fun providerGson(): Gson {
    return Gson()
}
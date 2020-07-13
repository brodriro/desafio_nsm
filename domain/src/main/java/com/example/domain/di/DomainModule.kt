package com.example.domain.di

import com.example.domain.usecases.SearchUseCase
import org.koin.dsl.module

val domainModule = module {
    single { SearchUseCase(get(), get()) }
}
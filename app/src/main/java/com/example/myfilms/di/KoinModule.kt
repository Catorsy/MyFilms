package com.example.myfilms.di

import com.example.myfilms.viewModel.MainViewModel
import com.example.myfilms.model.repository.Repository
import com.example.myfilms.model.repository.RepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single <Repository> { RepositoryImpl () }

    //ViewModels
    viewModel { MainViewModel (get()) }
    //гет возьмет эту часть: private val repository: Repository
}
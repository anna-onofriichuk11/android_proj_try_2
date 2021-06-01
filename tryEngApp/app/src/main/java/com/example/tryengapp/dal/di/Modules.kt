package com.example.tryengapp.dal.di


import com.example.tryengapp.api.TranslatorApi
import com.example.tryengapp.api.WordApi
import com.example.tryengapp.ui.views.base.BaseViewModel
import com.example.tryengapp.ui.views.viewmodels.UsersViewModel
import com.example.tryengapp.ui.views.viewmodels.ShDictionaryViewModel
import com.example.tryengapp.ui.views.viewmodels.HomeViewModel
import com.example.tryengapp.ui.views.viewmodels.TrainingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BaseViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { ShDictionaryViewModel(get(), get()) }
    viewModel { TrainingViewModel() }
    viewModel { UsersViewModel() }
}

val networkModule = module {
    single { WordApi.create() }
    single { TranslatorApi.create() }
}

val localDataSourceModule = module {
    single { PreferencesDataSource(get()) }
}
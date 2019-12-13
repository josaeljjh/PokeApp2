package com.jjh.pokeapp2.di.module


import com.jjh.pokeapp2.di.repository.DataRepository
import com.jjh.pokeapp2.di.repository.FirebaseRepository
import com.jjh.pokeapp2.interfaces.APIClientsModule
import com.jjh.pokeapp2.interfaces.APIQuestionsModule
import com.jjh.pokeapp2.utils.DataTempStatic
import com.jjh.pokeapp2.views.welcome.home.ViewModelHome
import com.jjh.pokeapp2.views.welcome.login.ViewModelLogin
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val repositoryModule: Module = module {
    single { DataRepository().getRetrofit<APIClientsModule>(DataTempStatic.getUrlBase()) }
    single { DataRepository().getRetrofit<APIQuestionsModule>(DataTempStatic.getUrlBase()) }
    single { DataRepository().provideGson() }
    single { FirebaseRepository() }
}

val viewModelModule: Module = module {
    viewModel { ViewModelLogin(get()) }
    viewModel { ViewModelHome(get()) }
}
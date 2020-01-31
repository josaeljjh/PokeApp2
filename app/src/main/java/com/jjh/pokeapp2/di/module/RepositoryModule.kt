package com.jjh.pokeapp2.di.module


import com.jjh.pokeapp2.repository.DataRepository
import com.jjh.pokeapp2.repository.FirebaseRepository
import com.jjh.pokeapp2.repository.InfoRepository
import com.jjh.pokeapp2.interfaces.APIClientsModule
import com.jjh.pokeapp2.interfaces.apiGexRetrofit
import com.jjh.pokeapp2.utils.DataTempStatic
import com.jjh.pokeapp2.views.home.region.ViewModelRegion
import com.jjh.pokeapp2.views.home.login.ViewModelLogin
import com.jjh.pokeapp2.views.home.select.ViewModelSelect
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module



val repositoryModule: Module = module {
    single { DataRepository().getRetrofit<APIClientsModule>(DataTempStatic.getUrlBase()) }
    single { DataRepository().getRetrofit<apiGexRetrofit>(DataTempStatic.getUrlBase()) }
    single { DataRepository().provideGson() }
    single { FirebaseRepository() }
    single { InfoRepository(get()) }
}

val viewModelModule: Module = module {
    viewModel { ViewModelLogin(get()) }
    viewModel { ViewModelRegion(get()) }
    viewModel { ViewModelSelect(get()) }
}
package com.jjh.pokeapp2.di.module


import com.jjh.pokeapp2.di.repository.DataRepository
import com.jjh.pokeapp2.interfaces.APIClientsModule
import com.jjh.pokeapp2.interfaces.APIQuestionsModule
import com.jjh.pokeapp2.utils.DataTempStatic
import org.koin.core.module.Module
import org.koin.dsl.module


object networkModule {

    val networkModule: Module = module {
        single { DataRepository().getRetrofit<APIClientsModule>(DataTempStatic.getUrlBase()) }
        single { DataRepository().getRetrofit<APIQuestionsModule>(DataTempStatic.getUrlBase()) }
        single { DataRepository().provideGson() }

    }
}
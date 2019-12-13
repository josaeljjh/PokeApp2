package com.jjh.pokeapp2.views.welcome.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jjh.pokeapp2.api.RegionResponseModel
import com.jjh.pokeapp2.interfaces.APIQuestionsModule
import com.jjh.pokeapp2.utils.extensions.launchAPIRequest
import retrofit2.await

class ViewModelHome(
    private val mRetrofit: APIQuestionsModule
) : ViewModel() {

    val regionResponse = MutableLiveData<RegionResponseModel>()
    val showLoading = MutableLiveData<Boolean>()
    fun obtenerRegiones() {
        launchAPIRequest {
            try {
                //showLoading.postValue(true)
                val result = mRetrofit.getRegions().await()
                regionResponse.postValue(result)
            }catch (e: Throwable){
                println(e.message)
                //resultNewOperation.postValue(null)
            }
            showLoading.postValue(false)
        }
    }

}
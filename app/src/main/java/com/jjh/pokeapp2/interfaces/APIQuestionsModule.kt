package com.jjh.pokeapp2.interfaces

import com.jjh.pokeapp2.api.RegionResponseModel
import retrofit2.Call
import retrofit2.http.GET


interface APIQuestionsModule {

    @GET("region/") //Regiones pokemon
    fun getRegions(): Call<RegionResponseModel>

    //@POST("SM003/") //service 2.3
    //fun productsUser(@Body request: UserProductsModel): Call<UserProductsResponseModel>
}
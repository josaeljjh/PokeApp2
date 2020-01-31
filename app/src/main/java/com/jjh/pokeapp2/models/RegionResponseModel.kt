package com.jjh.pokeapp2.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegionResponseModel(
    val count: Int = 0,
    val results: ArrayList<Result>
): Parcelable

@Parcelize
data class Result(
    val name: String = "",
    val url: String = ""
): Parcelable
{
    val nombre: String
        @SuppressLint("DefaultLocale")
        get() {
            return if(name.isNullOrEmpty()){
                ""
            }else{
                name.capitalize()
            }
        }

}
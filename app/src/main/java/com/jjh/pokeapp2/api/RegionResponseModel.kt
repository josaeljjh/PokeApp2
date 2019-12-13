package com.jjh.pokeapp2.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegionResponseModel(
    val count: Int = 0,
    val results: List<Result>
): Parcelable

@Parcelize
data class Result(
    val name: String = "",
    val url: String = ""
): Parcelable
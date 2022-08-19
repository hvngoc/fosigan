package com.waka.dana.na.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by hvngoc on 7/29/22
 */
data class WeatherList(
    @SerializedName("city")
    val city: City?,
    @SerializedName("cod")
    val cod: String?,
    @SerializedName("message")
    val message: Double?,
    @SerializedName("cnt")
    val cnt: Int?,
    @SerializedName("list")
    val list: List<Weather>?
)
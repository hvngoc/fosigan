package com.waka.dana.na.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by hvngoc on 7/29/22
 */
data class Weather(
    @SerializedName("dt")
    val dt: Long?,
    @SerializedName("sunrise")
    val sunrise: Long?,
    @SerializedName("sunset")
    val sunset: Long?,
    @SerializedName("temp")
    val temp: Temperature?,
    @SerializedName("feels_like")
    val feels_like: Temperature?,
    @SerializedName("pressure")
    val pressure: Int?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("weather")
    val weather: List<Predict>?,
    @SerializedName("speed")
    val speed: Double?,
    @SerializedName("deg")
    val deg: Double?,
    @SerializedName("gust")
    val gust: Double?,
    @SerializedName("clouds")
    val clouds: Double?,
    @SerializedName("pop")
    val pop: Double?,
    @SerializedName("rain")
    val rain: Double?,
)

data class Temperature(
    @SerializedName("day")
    val day: Double?,
    @SerializedName("min")
    val min: Double?,
    @SerializedName("max")
    val max: Double?,
    @SerializedName("night")
    val night: Double?,
    @SerializedName("eve")
    val eve: Double?,
    @SerializedName("morn")
    val morn: Double?,
)

data class Predict(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("main")
    val main: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("icon")
    val icon: String?,
)
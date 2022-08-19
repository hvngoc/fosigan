package com.waka.dana.na.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by hvngoc on 7/29/22
 */
data class City(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("coord")
    val coord: Coordinator?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("population")
    val population: Long,
    @SerializedName("timezone")
    val timezone: Long,
)

data class Coordinator(
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("lat")
    val lat: Double?
)
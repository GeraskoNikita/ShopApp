package com.example.shopapp.data.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Parcelize
@Serializable
data class RatingDto(
    @SerialName("count")
    val count: Int,
    @SerialName("rate")
    val rate: Double
): Parcelable
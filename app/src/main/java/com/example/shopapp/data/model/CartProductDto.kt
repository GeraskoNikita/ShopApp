package com.example.shopapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartProductDto(
    @SerialName("productId") val productId: Int,
    @SerialName("quantity") val quantity: Int
)

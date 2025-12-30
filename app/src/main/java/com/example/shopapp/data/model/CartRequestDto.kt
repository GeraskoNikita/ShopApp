package com.example.shopapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartRequestDto (
    @SerialName("userId") val userId: Int = 1,
    @SerialName("date") val date: String = "2025-12-20",
    @SerialName("products") val products: List<CartProductDto>
)


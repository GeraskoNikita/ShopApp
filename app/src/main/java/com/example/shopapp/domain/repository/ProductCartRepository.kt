package com.example.shopapp.domain.repository

import com.example.shopapp.domain.models.CartItem
import com.example.shopapp.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductCartRepository {
    val cartItems: Flow<List<CartItem>>

    suspend fun addToCart(product: Product)

    suspend fun clearCart()

    suspend fun checkout(): Result<String>
}
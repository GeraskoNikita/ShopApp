package com.example.shopapp.data.repository

import com.example.shopapp.data.datasource.StoreAPI
import com.example.shopapp.data.model.CartProductDto
import com.example.shopapp.data.model.CartRequestDto
import com.example.shopapp.domain.models.CartItem
import com.example.shopapp.domain.models.Product
import com.example.shopapp.domain.repository.ProductCartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductCartRepositoryImplementation(
    private val api: StoreAPI
) : ProductCartRepository {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    override val cartItems = _cartItems.asStateFlow()

    override suspend fun addToCart(product: Product) {
        _cartItems.update { currentList ->
            val existing = currentList.find { it.product.id == product.id }

            if (existing != null)
                currentList.map {
                    if (it.product.id == product.id) it.copy(quantity = it.quantity + 1)
                    else it
                }
            else currentList + CartItem(product, quantity = 1)
        }
    }

    override suspend fun clearCart() {
        _cartItems.value = emptyList()
    }

    override suspend fun checkout(): Result<String> {
        return try {
            val request = CartRequestDto(products = _cartItems.value.map {
                CartProductDto(
                    it.product.id,
                    it.quantity
                )
            })

            val resp = api.checkout(request)
            Result.success("The order #${resp.id} has been successfully placed.")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
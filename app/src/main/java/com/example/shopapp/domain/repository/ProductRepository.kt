package com.example.shopapp.domain.repository

import com.example.shopapp.domain.models.Product


interface ProductRepository {
    suspend fun getProducts(): List<Product>

    suspend fun getProductById(id: Int): Product
}
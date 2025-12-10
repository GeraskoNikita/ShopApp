package com.example.shopapp.data.api

import com.example.shopapp.data.model.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreAPI {
    @GET("products")
    suspend fun getAllProduct(): List<ProductDto>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto
}
package com.example.shopapp.data.datasource

import com.example.shopapp.data.model.CartRequestDto
import com.example.shopapp.data.model.CartResponseDto
import com.example.shopapp.data.model.ProductDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StoreAPI {
    @GET("products")
    suspend fun getAllProduct(): List<ProductDto>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto

    @POST("carts")
    suspend fun checkout(@Body cart: CartRequestDto): CartResponseDto
}
package com.example.shopapp.data.repository

import com.example.shopapp.data.api.RetrofitService
import com.example.shopapp.data.model.ProductDto
import retrofit2.Retrofit

class ProductRepository {
    suspend fun getAllProducts(): List<ProductDto>{
        return RetrofitService.api.getAllProduct()
    }

    suspend fun getProductById(id: Int): ProductDto{
        return RetrofitService.api.getProductById(id)
    }
}
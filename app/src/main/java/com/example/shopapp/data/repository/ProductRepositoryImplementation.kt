package com.example.month4.data.repository

import com.example.month4.data.mappers.toDomain
import com.example.shopapp.data.api.StoreAPI
import com.example.shopapp.domain.models.Product
import com.example.shopapp.domain.repository.ProductRepository


class ProductRepositoryImplementation(
    private val api: StoreAPI
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
       return api.getAllProduct().map { it.toDomain() }
    }

    override suspend fun getProductById(id: Int) = api.getProductById(id).toDomain()
}
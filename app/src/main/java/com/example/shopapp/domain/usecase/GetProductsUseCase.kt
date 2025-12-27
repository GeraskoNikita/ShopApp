package com.example.shopapp.domain.usecase

import com.example.shopapp.domain.repository.ProductRepository


class GetProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke() = repository.getProducts()
}
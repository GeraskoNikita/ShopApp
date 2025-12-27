package com.example.shopapp.domain.usecase

import com.example.shopapp.domain.repository.ProductRepository


class GetProductUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: Int) = repository.getProductById(id)
}
package com.example.month4.data.mappers

import com.example.shopapp.data.model.ProductDto
import com.example.shopapp.data.model.RatingDto
import com.example.shopapp.domain.models.Product
import com.example.shopapp.domain.models.Rating



fun ProductDto.toDomain(): Product {
    return Product(
        id = this.id ?: -1,
        category = this.category ?: "",
        description = this.description ?: "",
        image = this.image ?: "",
        price = this.price ?: 0.0,
        title = this.title ?: "",
        rating = this.ratingDto?.toDomain() ?: Rating.empty()
    )
}

fun RatingDto.toDomain(): Rating {
    return Rating(
        rate = this.rate ?: 0.0,
        count = this.count ?: 0
    )
}
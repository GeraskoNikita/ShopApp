package com.example.shopapp.ui.product_ditails.factory

import  com.example.shopapp.ui.product_ditails.viewmodel.ProductDetailsViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopapp.data.repository.ProductRepository

class ProductDetailsViewModelFactory(private val productId: Int) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductDetailsViewModel::class.java)) {
            return ProductDetailsViewModel(productId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
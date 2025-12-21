package com.example.shopapp.ui.product_ditails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.data.model.ProductDto
import com.example.shopapp.data.repository.ProductRepository
import com.example.shopapp.ui.models.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val productId: Int,

) : ViewModel() {
    private val repository= ProductRepository()
    private val _state = MutableStateFlow<UIState<ProductDto>>(UIState.Loading)
    val state: StateFlow<UIState<ProductDto>> = _state

    init {
        loadProduct()
    }

    private fun loadProduct() {
        viewModelScope.launch {
            _state.value = UIState.Loading
            try {
                val product = repository.getProductById(productId)
                _state.value = UIState.Success(product)
            } catch (e: Exception) {
                _state.value = UIState.Error(e.message ?: "Unknown error")
            }
        }
    }
}



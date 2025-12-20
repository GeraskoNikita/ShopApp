package com.example.shopapp.ui.product_list.viewmodel


import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope

import com.example.shopapp.data.model.ProductDto
import com.example.shopapp.data.repository.ProductRepository
import com.example.shopapp.ui.models.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductListViewModel : ViewModel() {
    private val repository = ProductRepository()

    private val _state = MutableStateFlow<UIState<List<ProductDto>>>(UIState.Loading)
    val state: StateFlow<UIState<List<ProductDto>>> = _state.asStateFlow()

    init {
        loadProducts()

    }

    private fun loadProducts() {


        viewModelScope.launch {
            _state.value = UIState.Loading
            try {
                val products = repository.getAllProducts()
                _state.value = UIState.Success(products)

            } catch (e: Exception) {
                _state.value= UIState.Error(e.toString() )

            }
        }
    }
}

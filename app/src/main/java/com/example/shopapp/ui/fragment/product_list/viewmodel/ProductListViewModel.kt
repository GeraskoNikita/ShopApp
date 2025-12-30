package com.example.shopapp.ui.fragment.product_list.viewmodel


import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope


import com.example.shopapp.domain.models.Product
import com.example.shopapp.domain.repository.ProductCartRepository
import com.example.shopapp.domain.usecase.GetProductsUseCase
import com.example.shopapp.ui.models.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val cartRepository: ProductCartRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UIState<List<Product>>>(UIState.Loading)
    val state: StateFlow<UIState<List<Product>>> = _state.asStateFlow()

    init {
        loadProducts()

    }

    private fun loadProducts() {


        viewModelScope.launch {
            _state.value = UIState.Loading
            try {
                val products = getProductsUseCase()
                _state.value = UIState.Success(products)

            } catch (e: Exception) {
                _state.value = UIState.Error(e.toString())

            }
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            cartRepository.addToCart(product)
        }
    }
}

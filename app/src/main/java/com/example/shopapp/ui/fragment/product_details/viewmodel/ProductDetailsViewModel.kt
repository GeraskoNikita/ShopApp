package com.example.shopapp.ui.fragment.product_details.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.shopapp.domain.models.Product
import com.example.shopapp.domain.usecase.GetProductUseCase
import com.example.shopapp.ui.models.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private   val productId : Int,
    private val getProductUseCase: GetProductUseCase

) : ViewModel() {

    private val _state = MutableStateFlow<UIState<Product>>(UIState.Loading)
    val state: StateFlow<UIState<Product>> = _state

    init {
        loadProduct()
    }

    private fun loadProduct() {
        Log.d("ololo", "loadProduct")
        viewModelScope.launch {
            _state.value = UIState.Loading
            try {
                val product = getProductUseCase(productId)
                _state.value = UIState.Success(product)
            } catch (e: Exception) {
                _state.value = UIState.Error(e.message ?: "Unknown error")
            }
        }
    }
}



package com.example.shopapp.ui.di


import com.example.shopapp.ui.fragment.product_details.viewmodel.ProductDetailsViewModel
import com.example.shopapp.ui.fragment.product_list.viewmodel.ProductListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { ProductListViewModel(getProductsUseCase = get()) }
    viewModel { (productId: Int) ->
        ProductDetailsViewModel(productId = productId, getProductUseCase = get())
    }

}
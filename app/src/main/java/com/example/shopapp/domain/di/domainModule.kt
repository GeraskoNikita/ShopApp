package com.example.shopapp.domain.di


import com.example.shopapp.domain.usecase.GetProductUseCase
import com.example.shopapp.domain.usecase.GetProductsUseCase
import com.example.shopapp.ui.fragment.product_details.viewmodel.ProductDetailsViewModel

import org.koin.dsl.module

val domainModule = module {
    factory { GetProductsUseCase(repository = get()) }
    factory { GetProductUseCase(repository = get())}
    factory { ProductDetailsViewModel(get(), get()) }

}
package com.example.shopapp.data.di

import com.example.month4.data.repository.ProductRepositoryImplementation
import com.example.shopapp.data.datasource.StoreAPI
import com.example.shopapp.data.repository.ProductCartRepositoryImplementation
import com.example.shopapp.domain.repository.ProductCartRepository
import com.example.shopapp.domain.repository.ProductRepository
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory


private val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}

private const val BASE_URL = "https://fakestoreapi.com"

val dataModule = module {
    single {
        json.asConverterFactory("application/json".toMediaType())
    }

    single<Interceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(get())
            .build()
            .create(StoreAPI::class.java)
    }

    single<ProductRepository> {
        ProductRepositoryImplementation(api = get())
    }

    single<ProductCartRepository> { ProductCartRepositoryImplementation(get()) }



}
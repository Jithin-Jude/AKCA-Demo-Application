package com.jithin.akcademoapplication.data.remote.api

import com.jithin.akcademoapplication.data.remote.dto.ProductDto
import retrofit2.http.GET

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): List<ProductDto>
}

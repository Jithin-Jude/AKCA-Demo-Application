package com.jithin.akcademoapplication.data.remote.datasource

import com.jithin.akcademoapplication.data.remote.dto.ProductDto

interface ProductRemoteDataSource {
    suspend fun getProducts(): List<ProductDto>
}

package com.jithin.akcademoapplication.data.remote.datasource

import com.jithin.akcademoapplication.data.remote.api.ProductApiService
import com.jithin.akcademoapplication.data.remote.dto.ProductDto
import javax.inject.Inject

class ProductRemoteDataSourceImpl @Inject constructor(
    private val apiService: ProductApiService
) : ProductRemoteDataSource {
    override suspend fun getProducts(): List<ProductDto> {
        return apiService.getProducts()
    }
}

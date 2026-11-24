package com.jithin.akcademoapplication.data.repository

import com.jithin.akcademoapplication.data.mapper.ProductMapper
import com.jithin.akcademoapplication.data.remote.datasource.ProductRemoteDataSource
import com.jithin.akcademoapplication.domain.model.Product
import com.jithin.akcademoapplication.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource,
    private val mapper: ProductMapper
) : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        val dtos = remoteDataSource.getProducts()
        return mapper.mapDtoListToDomainList(dtos)
    }
}

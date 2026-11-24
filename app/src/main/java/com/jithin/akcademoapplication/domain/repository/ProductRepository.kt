package com.jithin.akcademoapplication.domain.repository

import com.jithin.akcademoapplication.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}

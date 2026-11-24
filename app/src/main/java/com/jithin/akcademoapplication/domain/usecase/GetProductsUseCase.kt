package com.jithin.akcademoapplication.domain.usecase

import com.jithin.akcademoapplication.domain.model.Product
import com.jithin.akcademoapplication.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): List<Product> {
        return repository.getProducts()
    }
}

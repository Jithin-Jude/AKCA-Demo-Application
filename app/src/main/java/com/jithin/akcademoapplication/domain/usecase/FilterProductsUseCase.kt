package com.jithin.akcademoapplication.domain.usecase

import com.jithin.akcademoapplication.domain.model.Product
import javax.inject.Inject

class FilterProductsUseCase @Inject constructor() {
    operator fun invoke(products: List<Product>, category: String?): List<Product> {
        if (category == null || category.equals("All", ignoreCase = true)) {
            return products
        }
        return products.filter { it.category.equals(category, ignoreCase = true) }
    }
}

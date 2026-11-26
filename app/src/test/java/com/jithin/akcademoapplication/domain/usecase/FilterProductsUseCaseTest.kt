package com.jithin.akcademoapplication.domain.usecase

import com.jithin.akcademoapplication.domain.model.Product
import com.jithin.akcademoapplication.domain.model.Rating
import org.junit.Assert.assertEquals
import org.junit.Test

class FilterProductsUseCaseTest {

    private val useCase = FilterProductsUseCase()

    private val products = listOf(
        Product(1, "Prod 1", 10.0, "Desc", "electronics", "Img", Rating(4.0, 10)),
        Product(2, "Prod 2", 20.0, "Desc", "jewelery", "Img", Rating(4.5, 20)),
        Product(3, "Prod 3", 30.0, "Desc", "electronics", "Img", Rating(4.2, 15))
    )

    @Test
    fun invoke_withNullCategory_returnsAllProducts() {
        val result = useCase(products, null)
        assertEquals(products, result)
    }

    @Test
    fun invoke_withAllCategory_returnsAllProducts() {
        val result = useCase(products, "All")
        assertEquals(products, result)
    }

    @Test
    fun invoke_withSpecificCategory_returnsFilteredProducts() {
        val result = useCase(products, "electronics")
        assertEquals(2, result.size)
        assertEquals(listOf(products[0], products[2]), result)
    }
}

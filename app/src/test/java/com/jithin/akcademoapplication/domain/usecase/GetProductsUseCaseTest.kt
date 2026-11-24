package com.jithin.akcademoapplication.domain.usecase

import com.jithin.akcademoapplication.domain.model.Product
import com.jithin.akcademoapplication.domain.model.Rating
import com.jithin.akcademoapplication.domain.repository.ProductRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetProductsUseCaseTest {

    private val repository: ProductRepository = mockk()
    private val useCase = GetProductsUseCase(repository)

    @Test
    fun invoke_returnsProductsFromRepository() = runTest {
        val mockProducts = listOf(
            Product(1, "Prod 1", 10.0, "Desc", "Cat", "Img", Rating(4.0, 10))
        )
        coEvery { repository.getProducts() } returns mockProducts

        val result = useCase()

        assertEquals(mockProducts, result)
        coVerify(exactly = 1) { repository.getProducts() }
    }
}

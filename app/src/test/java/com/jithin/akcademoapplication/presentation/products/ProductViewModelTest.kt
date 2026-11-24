package com.jithin.akcademoapplication.presentation.products

import com.jithin.akcademoapplication.domain.model.Product
import com.jithin.akcademoapplication.domain.model.Rating
import com.jithin.akcademoapplication.domain.usecase.GetProductsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductViewModelTest {

    private val getProductsUseCase: GetProductsUseCase = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadProducts_successState() = runTest {
        val products = listOf(
            Product(1, "Test", 10.0, "Desc", "Cat", "Img", Rating(4.5, 20))
        )
        coEvery { getProductsUseCase() } returns products

        val viewModel = ProductViewModel(getProductsUseCase)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(ProductUiState.Success(products), state)
    }

    @Test
    fun loadProducts_emptyState() = runTest {
        coEvery { getProductsUseCase() } returns emptyList()

        val viewModel = ProductViewModel(getProductsUseCase)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(ProductUiState.Empty, state)
    }

    @Test
    fun loadProducts_errorState() = runTest {
        coEvery { getProductsUseCase() } throws RuntimeException("Network error")

        val viewModel = ProductViewModel(getProductsUseCase)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state is ProductUiState.Error)
        assertEquals("Network error", (state as ProductUiState.Error).message)
    }
}

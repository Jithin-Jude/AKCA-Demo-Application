package com.jithin.akcademoapplication.presentation.products

import com.jithin.akcademoapplication.domain.model.Product

sealed interface ProductUiState {
    data object Loading : ProductUiState
    data class Success(val products: List<Product>) : ProductUiState
    data class Error(val message: String) : ProductUiState
    data object Empty : ProductUiState
}

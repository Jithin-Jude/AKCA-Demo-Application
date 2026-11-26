package com.jithin.akcademoapplication.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jithin.akcademoapplication.domain.usecase.FilterProductsUseCase
import com.jithin.akcademoapplication.domain.usecase.GetProductsUseCase
import com.jithin.akcademoapplication.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val filterProductsUseCase: FilterProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    private var allProducts: List<Product> = emptyList()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = ProductUiState.Loading
            try {
                val products = getProductsUseCase()
                if (products.isEmpty()) {
                    _uiState.value = ProductUiState.Empty
                } else {
                    allProducts = products
                    val categories = listOf("All") + products.map { it.category }.distinct()
                    val filtered = filterProductsUseCase(products, "All")
                    _uiState.value = ProductUiState.Success(
                        products = filtered,
                        categories = categories,
                        selectedCategory = "All"
                    )
                }
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                _uiState.value = ProductUiState.Error(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            }
        }
    }

    fun onCategorySelected(category: String) {
        val currentState = _uiState.value
        if (currentState is ProductUiState.Success) {
            val filtered = filterProductsUseCase(allProducts, category)
            _uiState.value = currentState.copy(
                products = filtered,
                selectedCategory = category
            )
        }
    }
}

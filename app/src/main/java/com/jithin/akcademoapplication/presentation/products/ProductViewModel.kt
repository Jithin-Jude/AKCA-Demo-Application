package com.jithin.akcademoapplication.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jithin.akcademoapplication.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

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
                    _uiState.value = ProductUiState.Success(products)
                }
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                _uiState.value = ProductUiState.Error(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            }
        }
    }
}

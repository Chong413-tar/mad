package com.example.inventorymanage.ui.theme

import androidx.lifecycle.ViewModel
import com.example.inventorymanage.data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(Product())
    val uiState: StateFlow<Product> = _uiState.asStateFlow()

    fun setCategory(category: String) {
        _uiState.update { currentState ->
            currentState.copy(
                category = category
            )
        }
    }

    fun setName(name: String) {
        _uiState.update { currentState ->
            currentState.copy(
                name = name
            )
        }
    }

    fun setCPrice(costPrice: Double) {
        _uiState.update { currentState ->
            currentState.copy(
                costPrice = costPrice,
                profit = calculateProfit(costPrice, currentState.salePrice)
            )
        }
    }

    fun setSPrice(salePrice: Double) {
        _uiState.update { currentState ->
            currentState.copy(
                salePrice = salePrice,
                profit = calculateProfit(currentState.costPrice, salePrice)
            )
        }
    }

    fun setUnit(productUnit: String) {
        _uiState.update { currentState ->
            currentState.copy(
                productUnit = productUnit
            )
        }
    }

    fun setBarcode(barcode: String) {
        _uiState.update { currentState ->
            currentState.copy(
                barcode = barcode
            )
        }
    }

    fun reset() {
        _uiState.value = Product()
    }

    private fun calculateProfit(costPrice: Double, salePrice: Double): Double {
        return salePrice - costPrice
    }
}

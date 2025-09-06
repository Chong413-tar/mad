package com.example.inventorymanage.data

data class Product(
    val category: String ="",
    val name: String = "",
    val costPrice: Double = 0.0,
    val salePrice: Double = 0.0,
    val productUnit: String = "",
    val barcode:String = "",
    val profit:Double = 0.0
)

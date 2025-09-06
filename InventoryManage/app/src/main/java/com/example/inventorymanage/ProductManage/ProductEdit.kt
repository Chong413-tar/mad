package com.example.inventorymanage.ProductManage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventorymanage.R
import com.example.inventorymanage.data.Product
import com.example.inventorymanage.ui.theme.InventoryManageTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductEditScreen(
    product: Product,
    onCancel: () -> Unit,
    onConfirm: (Product) -> Unit
) {
    var name by remember { mutableStateOf(product.name) }
    var category by remember { mutableStateOf(product.category) }
    var unit by remember { mutableStateOf(product.productUnit) }
    var costPrice by remember { mutableStateOf(product.costPrice.toString()) }
    var salePrice by remember { mutableStateOf(product.salePrice.toString()) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onCancel() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Text(
                        text = "Edit Product",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.white),
                    titleContentColor = Color.Black
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.lessred)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Overview",
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                    Text(
                        text = "----------------------------------------------------------------"
                    )
                    RowDetailEditable("Name", product.name) { name = it }
                    RowDetailEditable("Category", product.category) { category = it }
                    RowDetailEditable("Unit", product.productUnit) { unit = it }
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Purchase Information",
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                    RowDetailEditable("Cost Price", product.costPrice.toString()) { costPrice = it }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Sales",
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                    RowDetailEditable("Sale Price", product.salePrice.toString()) { salePrice = it }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { onCancel() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.width(170.dp).height(140.dp)) {
                    Text("Cancel", fontSize = 25.sp)
                }
                Button(
                    onClick = {
                    val updated = product.copy(
                        name = name,
                        category = category,
                        productUnit = unit,
                        costPrice = costPrice.toDoubleOrNull() ?: product.costPrice,
                        salePrice = salePrice.toDoubleOrNull() ?: product.salePrice
                    )
                    onConfirm(updated)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.width(170.dp).height(140.dp)
                ) {
                    Text("Confirm", fontSize = 25.sp)
                }
            }
        }
    }
}

@Composable
fun RowDetailEditable(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf(value) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label:",
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                onValueChange(it)
            },
            singleLine = true,
            modifier = Modifier.width(160.dp),
            textStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.Bold),
            placeholder = {
                Text(text = value, color = Color.Gray)
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit"
                )
            },
            shape = RoundedCornerShape(20.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductEditPreview() {
    InventoryManageTheme {
        ProductEditScreen(
            product = Product(
                name = "Coca Cola",
                costPrice = 1.50,
                salePrice = 2.00,
                productUnit = "24",
                barcode = "1234567890123",
                category = "Drink"
            ),
            onCancel = {},
            onConfirm = {}
        )
    }
}


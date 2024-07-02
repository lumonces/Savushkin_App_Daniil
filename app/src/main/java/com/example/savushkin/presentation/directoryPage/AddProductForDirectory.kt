package com.example.savushkin.presentation.directoryPage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savushkin.domain.models.Directory

@Composable
fun AddProductForDirectory(product : Directory) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "Код: ${product.codeProduct}", fontSize = 18.sp)
            Text(text = "Название: ${product.nameProduct}", fontSize = 18.sp)
            Text(text = "Температура: ${product.temperature}", fontSize = 18.sp)
            Text(text = "EAN13: ${product.ean13}", fontSize = 18.sp)
        }

    }
}
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

@Composable
fun AddProductForDirectory() {
    // ЗДЕСЬ Я ПО ID БУДУ ПОЛУЧАТЬ ОБЪЕКТ ПРОДУКТА ДЛЯ СПРАВОЧНИКА И ВЫВОДИТЬ ИНФОРМАЦИЮ НИЖЕ
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
            Text(text = "Код: 010101", fontSize = 18.sp)
            Text(text = "Название: Масло", fontSize = 18.sp)
            Text(text = "Температура: +5", fontSize = 18.sp)
            Text(text = "EAN13: 4810268000485", fontSize = 18.sp)
        }

    }
}
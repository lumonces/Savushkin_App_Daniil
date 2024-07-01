package com.example.savushkin.presentation.directoryPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContentForDirectoryPage() {
    LazyColumn(
        modifier = Modifier
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // ЗДЕСЬ БУДУ ПОЛУЧАТЬ ВСЕ КОДЫ ПРОДУКТОВ ДЛЯ СПРАВОЧНИКА И ВЫВОДИТЬ НИЖЕ
        items(10) {
            AddProductForDirectory()
        }
    }
}
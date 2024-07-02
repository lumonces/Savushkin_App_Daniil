package com.example.savushkin.presentation.directoryPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.savushkin.presentation.MainViewModel

@Composable
fun ContentForDirectoryPage(vm : MainViewModel) {
    val directoryList by vm.directoryList.observeAsState(listOf())
    LazyColumn(
        modifier = Modifier
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(directoryList) {obj ->
            AddProductForDirectory(obj)
        }
    }
}
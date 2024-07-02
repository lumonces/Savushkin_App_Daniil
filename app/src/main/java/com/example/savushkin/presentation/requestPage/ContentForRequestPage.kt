package com.example.savushkin.presentation.requestPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.savushkin.domain.models.Request
import com.example.savushkin.presentation.MainViewModel

@Composable
fun ContentForRequestPage(request : Request, vm : MainViewModel) {
    val productsOfRequest = vm.getProductsOfRequest(request.numberRequest)
    LazyColumn(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(productsOfRequest) {
            AddProductForRequest(product = it, vm = vm)
        }
    }
}
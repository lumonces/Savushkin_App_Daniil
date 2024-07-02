package com.example.savushkin.presentation.allRequestsPage

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
fun ContentForAllRequestsPage(navigateToRequestPage : () -> Unit, vm : MainViewModel) {
    val requestsList by vm.requestsList.observeAsState(listOf())
    LazyColumn(
        modifier = Modifier
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(requestsList) {
            AddNewRequest(navigateToRequestPage, vm, it)
        }
    }
}
package com.example.savushkin.presentation.allRequestsPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savushkin.domain.models.Request
import com.example.savushkin.presentation.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewRequest(navigateToRequestPage : () -> Unit, vm : MainViewModel, request : Request) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = {
            vm.setNumberRequest(request.numberRequest)
            navigateToRequestPage()
        }
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "№${request.numberRequest}", fontSize = 18.sp)
                Text(text = request.dateRequest, fontSize = 18.sp)
            }
            Text(text = "Название: ${request.nameShop}", fontSize = 18.sp)
            Text(text = "Адрес: ${request.addressShop}", fontSize = 18.sp)
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(text = request.statusRequest, color = Color.Red, fontSize = 20.sp)
            }
        }

    }
}
package com.example.savushkin.presentation.requestPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savushkin.R
import com.example.savushkin.presentation.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestPage(vm: MainViewModel, launchScan: () -> Unit) {
    val request = vm.getRequestByNumber(vm.getNumberRequest())
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFF0088EB))
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "ЗАЯВКА №${request.numberRequest}", fontSize = 28.sp)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0088EB),
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Button(
                        enabled = !vm.getStatusAllScan(),
                        onClick = {
                            vm.setCurrentRequest(request = request)
                            launchScan()
                        },
                        modifier = Modifier
                            .clip(CircleShape),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0088EB),
                            contentColor = Color.White
                        )
                    ) {
                        Image(
                            bitmap = ImageBitmap.imageResource(R.drawable.scan),
                            contentDescription = "scan",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }

                Button(
                    enabled = vm.getStatusAllScan(),
                    shape = RectangleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .height(60.dp),
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0088EB),
                        contentColor = Color.White
                    )
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("Отправить", fontSize = 28.sp)
                    }
                }
            }

        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = Color(0xFFE6E6E6)
        ) {
            ContentForRequestPage(request = request, vm = vm)
        }

    }
}
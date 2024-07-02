package com.example.savushkin.presentation.allRequestsPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savushkin.R
import com.example.savushkin.presentation.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllRequestsPage(
    vm : MainViewModel,
    navigateToRequestPage : () -> Unit,
    navigateToDirectoryPage : () -> Unit
) {
    Scaffold (
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
                        Box(modifier = Modifier.weight(1f))

                        Box(
                            modifier = Modifier
                                .background(Color(0xFF0088EB))
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "ЗАЯВКИ", fontSize = 28.sp)
                        }

                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(45.dp)
                                    .clip(CircleShape)
                                    .clickable { navigateToDirectoryPage() },
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    bitmap = ImageBitmap.imageResource(R.drawable.directory),
                                    contentDescription = "directory",
                                    contentScale = ContentScale.Inside,
                                    modifier = Modifier.size(30.dp)
                                )
                            }

                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0088EB),
                    titleContentColor = Color.White
                )
            )
        }
    ) {innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = Color(0xFFE6E6E6)
        ) {
            ContentForAllRequestsPage(navigateToRequestPage = navigateToRequestPage, vm = vm)
        }

    }
}
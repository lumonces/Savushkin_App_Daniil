package com.example.savushkin.presentation.requestPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
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
import com.example.savushkin.domain.models.ProductOfRequest
import com.example.savushkin.presentation.MainViewModel

@Composable
fun AddProductForRequest(product : ProductOfRequest, vm : MainViewModel) {
    val productOfDirectory = vm.getProductOfDirectoryByCode(product.codeProduct)
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row (
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column (
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Код: ${product.codeProduct}", fontSize = 18.sp)
                Text(text = "Название: ${productOfDirectory[0].nameProduct}", fontSize = 18.sp)
                Text(text = "Количество: ${product.quantity}", fontSize = 18.sp)
                Text(text = "EAN13: ${productOfDirectory[0].ean13}", fontSize = 18.sp)
            }

            Box(
                contentAlignment = Alignment.CenterEnd
            ) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    if(product.quantity == 0) {
                        Image(
                            bitmap = ImageBitmap.imageResource(R.drawable.mark),
                            contentDescription = "mark",
                            contentScale = ContentScale.Inside,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    else {
                        Image(
                            bitmap = ImageBitmap.imageResource(R.drawable.cross),
                            contentDescription = "cross",
                            contentScale = ContentScale.Inside,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                }

            }
        }
    }
}
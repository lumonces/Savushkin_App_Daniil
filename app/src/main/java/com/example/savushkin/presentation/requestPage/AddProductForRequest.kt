package com.example.savushkin.presentation.requestPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

@Composable
fun AddProductForRequest() {
    // ЗДЕСЬ Я БУДУ ПОЛУЧАТЬ ОБЪЕКТ ПРОДУКТА ДЛЯ ЗАЯВКИ И ВЫВОДИТЬ ИНФОРМАЦИЮ НИЖЕ
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
            val count = 3
            Column (
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Код: 010101", fontSize = 18.sp)
                Text(text = "Название: Молоко", fontSize = 18.sp)
                Text(text = "Количество: $count", fontSize = 18.sp)
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
                    if(count == 0) {
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
package com.example.savushkin.presentation.authorizationPage

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savushkin.presentation.MainViewModel

@Composable
fun AuthorizationPage(
    context : Context,
    vm : MainViewModel,
    navigateToAllRequestsPage : () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFE6E6E6)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                TextField(
                    value = vm.getLogin(),
                    onValueChange = { vm.setLogin(it) },
                    placeholder = { Text(text = "Login", fontSize = 22.sp, color = Color.Gray) },
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(fontSize = 24.sp),
                    modifier = Modifier.fillMaxWidth().height(60.dp)
                )

                TextField(
                    value = vm.getPassword(),
                    onValueChange = { vm.setPassword(it) },
                    placeholder = { Text(text = "Password", fontSize = 22.sp, color = Color.Gray) },
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(fontSize = 24.sp),
                    modifier = Modifier.fillMaxWidth().height(60.dp)
                )

                Button(
                    shape = RectangleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .height(60.dp),
                    onClick = {
                        val toast = Toast.makeText(context, "Введён неверный Логин и/или Пароль", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.BOTTOM, 0, 0)
                        vm.checkAuth(navigateToAllRequestsPage, toast)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0088EB),
                        contentColor = Color.White
                    )
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("Войти", fontSize = 28.sp)
                    }
                }


                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(
                        checked = vm.getStatusRememberEnter(),
                        onCheckedChange = { vm.setStatusRememberEnter(it) }
                    )
                    Text(text = "Запомнить", fontSize = 20.sp)
                }
            }
        }
    }
}
package com.yunshen.yunapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.yunshen.yunapp.R
import com.yunshen.yunapp.viewmodel.UIViewModel

@Composable
fun LoginScreen(modifier: Modifier = Modifier, viewModel: UIViewModel = viewModel()) {
    var username = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var selected by remember { mutableIntStateOf(0) }
    var email = remember { mutableStateOf("") }
    var code = remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = uiState.image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.hhead)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .alpha(0.8f)
                    .align(Alignment.CenterHorizontally)
                    .height(500.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(color = Color.White)
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        val tabs =  listOf("登录", "注册")
                        tabs.forEachIndexed { index, title ->
                            Text(
                                text = title,
                                color = if (selected == index) Color.Blue else Color.Gray,
                                modifier = Modifier.clickable { selected = index }
                            )
                        }

                    }
                    HorizontalDivider(color = Color.Black, thickness = 1.dp)
                    when(selected){
                        0 -> Login(username, password)
                        1 -> Register(username, email, code, password)
                    }
                }
            }
        }
    }
}


@Composable
private fun Login(username: MutableState<String>, password: MutableState<String>){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(value = username.value,
            maxLines = 1,
            onValueChange = { username.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "邮箱") })

        OutlinedTextField(
            value = password.value,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { password.value = it },
            label = { Text(text = "密码") },
            visualTransformation = PasswordVisualTransformation()
        )

        Button(onClick = {}, modifier = Modifier.fillMaxWidth(0.9f)) {
            Text("登录")
        }
    }
}


@Composable
fun Register(
    username: MutableState<String>,
    email: MutableState<String>,
    code: MutableState<String>,
    password: MutableState<String>
) {
    var text by remember { mutableStateOf("发送验证码") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = username.value,
            maxLines = 1,
            onValueChange = { username.value = it },
            label = { Text("用户名") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = email.value,
            maxLines = 1,
            onValueChange = { email.value = it },
            label = { Text("邮箱") },
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = code.value,
                maxLines = 1,
                onValueChange = { code.value = it },
                label = { Text("验证码") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = { /* 发送验证码 */ },
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
                    .padding(6.dp)
            ) {
                Text(text, fontSize = 10.sp, maxLines = 1, onTextLayout = {
                    textLayoutResult ->
                    if (textLayoutResult.hasVisualOverflow){
                        text = "获取"
                    }
                })
            }
        }
        OutlinedTextField(
            value = password.value,
            maxLines = 1,
            onValueChange = { password.value = it },
            label = { Text("密码") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            Text("注册")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPrivate() {
    LoginScreen()
}
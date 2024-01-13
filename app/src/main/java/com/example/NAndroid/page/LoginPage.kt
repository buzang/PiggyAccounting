package com.example.NAndroid.page

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.NAndroid.ui.theme.MyApplicationTheme
import com.example.NAndroid.utlis.DBHelper

class LoginPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginForm()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(modifier: Modifier = Modifier) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    var context = LocalContext.current

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    username = ""
                    password = ""
                }) {
                    Text("确定")
                }
            },
            title = { Text("提示") },
            text = { Text("不存在这个账号或密码不正确") }
        )
    }

    val colors = listOf(Color(94, 6, 234), Color(255, 255, 255, 5)) // 定义渐变的起始和结束颜色

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors))
    ) {
        Column(
            modifier = modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                text = "🐷",
                style = TextStyle(
                    fontSize = 56.sp,
                ),
                modifier = modifier
                    .padding(top = 120.dp)
                    .padding(bottom = 10.dp)
                    .shadow(20.dp)
            )
            Text(
                text = "小猪记账", style = TextStyle(
                    fontSize = 56.sp,
                    fontWeight = FontWeight(1000)
                ),
                color = Color.White,
                modifier = modifier.padding(bottom = 50.dp)
            )
            TextField(
                value = password,
                label = { Text(text = "🔒") },
                onValueChange = { newAmount ->
                    password = newAmount
                },
                modifier = Modifier
                    .padding(12.dp)
                    .border(2.dp, Color(252, 211, 178), RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    cursorColor = Color(252, 211, 178),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(8.dp),
                singleLine = true,

                placeholder = {
                    Text(
                        text = "Password",
                        color = Color.Gray
                    )
                },
            )
            Column {
                Button(
                    onClick = {
                        handleLogin(username, password, success = {
                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                        }, fail = {
                            showDialog = true
                        })
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(
                            122, 59, 234
                        ),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.padding(top = 100.dp),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = "登录",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontWeight = FontWeight(1000)
                        ),
                        modifier = Modifier.width(200.dp),
                    )
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = "注册",
            modifier = modifier
                .align(Alignment.BottomCenter)
                .width(200.dp)
                .padding(bottom = 15.dp)
                .clickable(onClick = {
                    val intent = Intent(context, RegisterPage::class.java)
                    context.startActivity(intent)
                }),
            color = Color(92, 15, 230),
            textAlign = TextAlign.Center,
        )
    }
}


fun handleLogin(uname: String, pwd: String, success: () -> Unit, fail: () -> Unit) {
    Thread {
        var result = DBHelper.use().userInfoDao().login( password = pwd)
        Log.i("###############登录", result.toString())
        if (result != null) {
            success()
        } else {
            fail()
        }
    }.start()
}

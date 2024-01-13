package com.example.NAndroid.page

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.NAndroid.data.entities.UserInfo
import com.example.NAndroid.ui.theme.MyApplicationTheme
import com.example.NAndroid.utlis.DBHelper

class RegisterPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                    Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterForm()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterForm(modifier: Modifier = Modifier) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }

    var context = LocalContext.current

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    val intent = Intent(context, LoginPage::class.java)
                    context.startActivity(intent)
                }) {
                    Text("确定")
                }
            },
            title = { Text("提示") },
            text = { Text("注册成功") }
        )
    }

    val colors = listOf(Color(94, 6, 234), Color(255, 255, 255, 5)) // 定义渐变的起始和结束颜色


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors))
    ) {
        Column(
            modifier = modifier.align(Alignment.TopCenter).padding(top = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                text = "📝注册", style = TextStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight(1000)
                ),
                color = Color.White,
                modifier = modifier
                    .padding(10.dp)
            )
            TextField(
                value = password, // 这是一个字符串
                label = { Text(text = "🔒 Password") },
                onValueChange = { newAmount ->
                    password = newAmount
                },
                modifier = Modifier
                    .padding(12.dp).padding(top = 50.dp) // 根据需要调整内边距
                    .border(2.dp, Color(252, 211, 178), RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White, // 白色背景
                    cursorColor = Color(252, 211, 178), // 紫色光标
                    focusedIndicatorColor = Color.Transparent, // 透明聚焦指示器
                    unfocusedIndicatorColor = Color.Transparent // 透明非聚焦指示器
                ),
                shape = RoundedCornerShape(8.dp),  // 圆角形状
                singleLine = true,

                placeholder = {
                    Text(
                        text = "Password",
                        color = Color.Gray
                    )
                },
            )

            TextField(
                value = name,
                label = { Text(text = "🐷 Name") },
                onValueChange = { newAmount ->
                    name = newAmount
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
                shape = RoundedCornerShape(8.dp),
                singleLine = true,

                placeholder = {
                    Text(
                        text = "Password",
                        color = Color.Gray
                    )
                },
            )



            TextField(
                value = phone, // 这是一个字符串
                label = { Text(text = "📱 Contact") },
                onValueChange = { newAmount ->
                    phone = newAmount
                },
                modifier = Modifier
                    .padding(12.dp) // 根据需要调整内边距
                    .border(2.dp, Color(252, 211, 178), RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White, // 白色背景
                    cursorColor = Color(252, 211, 178), // 紫色光标
                    focusedIndicatorColor = Color.Transparent, // 透明聚焦指示器
                    unfocusedIndicatorColor = Color.Transparent // 透明非聚焦指示器
                ),
                shape = RoundedCornerShape(8.dp),  // 圆角形状
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
                        handleRegister(username, password, name, phone) {
                            Handler(Looper.getMainLooper()).post {
                                showDialog = true
                            }
                        }
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
                        text = "注册",
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
}


fun handleRegister(uname: String, pwd: String, name: String, phone: String) {

}

fun handleRegister(
    uname: String,
    pwd: String,
    name: String,
    phone: String,
    onRegisterComplete: () -> Unit
) {
    Thread {
        DBHelper.use().userInfoDao().insertAll(
            UserInfo(userName = "admin", password = pwd, phone = phone, name = name)
        )
        onRegisterComplete() // 调用回调函数
    }.start()
}
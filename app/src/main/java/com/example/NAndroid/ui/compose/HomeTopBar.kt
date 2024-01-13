package com.example.NAndroid.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeTopBar(){
    val colors = listOf(Color(94, 6, 234), Color(255, 255, 255, 5)) // 定义渐变的起始和结束颜色

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Brush.verticalGradient(colors))
    ) {
        // 浮动的按钮

        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.height(60.dp)) {
            // 您的内容
            Text(
                text = "小猪记账",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(35.dp),
                color = Color(255, 255, 255),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(1000)
                )
            )
        }
    }
}
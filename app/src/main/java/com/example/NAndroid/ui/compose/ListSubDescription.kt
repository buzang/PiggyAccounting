package com.example.NAndroid.ui.compose

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ListSubDescription(
    DateTime: String, Incomings: Double,
    Outgoings: Double,
) {
    Box(
        Modifier
            .padding(top = 170.dp)
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Column {

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = DateTime, style = TextStyle(
                        color = Color(99, 110, 114),
                        fontSize = 13.sp,
                    )
                )
                Row(
                    Modifier
                        .widthIn(150.dp, 300.dp)
                        .align(Alignment.Bottom),
                    horizontalArrangement = Arrangement.SpaceEvenly,

                    ) {
                    Text(
                        text = "收入: ${Incomings}",
                        textAlign = TextAlign.End,
                        style = TextStyle(
                            color = Color(99, 110, 114),
                            fontSize = 12.sp,

                            ),
                        modifier = Modifier.widthIn(75.dp, 150.dp)
                    )
                    Text(

                        text = "支出：${Outgoings}", textAlign = TextAlign.End, style = TextStyle(
                            color = Color(99, 110, 114),
                            fontSize = 12.sp,
                        ),
                        modifier = Modifier.widthIn(75.dp, 150.dp).padding(horizontal = 10.dp)
                    )
                }
            }
        }
    }

}
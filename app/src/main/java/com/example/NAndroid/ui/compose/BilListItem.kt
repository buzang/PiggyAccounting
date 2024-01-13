package com.example.NAndroid.ui.compose

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.AssistantPhoto
import androidx.compose.material.icons.filled.Chair
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BillListItem(
    billId: Int,
    categoryId: Int,
    name: String,
    amount: Double,
    type: Int,
    snackbarHostState: SnackbarHostState,
    callback: (billId: Int) -> Unit
) {
    val scope = rememberCoroutineScope()

    Row(
        Modifier
            .combinedClickable(
                onClick = { /* 处理点击事件 */
                    scope.launch {
                        val result = snackbarHostState
                            .showSnackbar(
                                message = "是否删除此记录",
                                actionLabel = "删除",
                                // Defaults to SnackbarDuration.Short
                                duration = SnackbarDuration.Indefinite
                            )
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                callback(billId)
                            }

                            SnackbarResult.Dismissed -> {
                                Log.i("#######", "######EEEE####")
                                /* Handle snackbar dismissed */
                                snackbarHostState.currentSnackbarData?.dismiss()
                            }
                        }
                    }
                },
                onLongClick = {

                }
            )
            .padding(top = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconSwitch(categoryId)
                }
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,

                    ) {
                    Text(text = name)
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                ) {
                    Text(
                        text = if (type == 1) "- $amount" else "${amount.toString()}",
                        textAlign = TextAlign.End,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(300)
                        )
                    )
                }
            }

            // 下边框
            Box(
                modifier = Modifier
                    .width(350.dp)
                    .padding(top = 10.5.dp)
                    .background(Color(99, 110, 114, 15))
                    .height(1.dp)
            ) { }
        }
    }


}

@Composable
fun IconSwitch(categoryId: Int) {

    when (categoryId) {
        1 -> Text(
            text = "🥘", style = TextStyle(
                fontSize = 22.sp,
            )
        )

        2 -> Text(
            text = "🛍️", style = TextStyle(
                fontSize = 22.sp,
            )
        )

        3 -> Text(
            text = "🚕", style = TextStyle(
                fontSize = 22.sp,
            )
        )

        4 -> Text(
            text = "🏠", style = TextStyle(
                fontSize = 22.sp,
            )
        )

        5 -> Text(
            text = "💸", style = TextStyle(
                fontSize = 22.sp,
            )
        )

        6 -> Text(
            text = "❤️‍", style = TextStyle(
                fontSize = 22.sp,
            )
        )

        7 -> Text(
            text = "🛋", style = TextStyle(
                fontSize = 22.sp,
            )
        )

        8 -> Text(
            text = "🏦", style = TextStyle(
                fontSize = 22.sp,
            )
        )

        9 -> Text(
            text = "👨‍💻", style = TextStyle(
                fontSize = 22.sp,
            )
        )

        10 -> Text(
            text = "💵", style = TextStyle(
                fontSize = 22.sp,
            )
        )
    }
}
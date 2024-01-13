package com.example.NAndroid.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.NAndroid.data.entities.Bill
import java.util.Calendar


@Composable
fun BillList(
    bill: List<Bill>,
    snackbarHostState: SnackbarHostState,
    _callback: (billId: Int) -> Unit
) {

    Box(
        Modifier
            .padding(top = 190.dp)
            .padding(10.dp)
            .fillMaxWidth()
    ) {

        LazyColumn {
            items(bill) { billItem ->
                // 每个账单项的渲染方式
                BillListItem(
                    billId = billItem.id,
                    name = billItem.remark,
                    amount = billItem.amount,
                    categoryId = billItem.categoryId,
                    type = billItem.type,
                    snackbarHostState = snackbarHostState,
                ) { _callback(it) }
            }
        }

        if (bill.isEmpty())

            Box(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🧾",
                        style = TextStyle(
                            fontSize = 36.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()

                    )
                    Text(
                        text = "记一笔",
                        textAlign = TextAlign.Center,

                        style = TextStyle(
                            fontSize = 16.sp, color = Color(190, 195, 199)
                        ),

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp)
                    )
                }

            }

    }
}
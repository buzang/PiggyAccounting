package com.example.NAndroid.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun BillSubDescription(
    year: String,
    month: String,
    monthTotalIncomings: Double,
    monthTotalOutgoings: Double,
    openDialog: MutableState<Boolean>
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .padding(top = 30.dp)
            .height(70.dp)
    ) {
        Row {
            Row(
                Modifier
                    .width(100.dp)
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable(onClick = {
                        openDialog.value = true
                    })
                ) {
                    Text(
                        text = "${if (year != null) year else 0}年",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = Color.White
                        ),
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "${if (month != null) month else 0}",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Color.White
                            ),
                        )
                        Text(
                            text = "月",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = Color.White
                            ),
                        )
                        Icon(
                            Icons.Filled.ArrowDropDown,
                            contentDescription = "添加",
                            modifier = Modifier.size(20.dp),
                            tint = Color.White
                        )
                    }
                }
            }

            Row(
                Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "|",
                    style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight(100)),
                    color = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier
                        .fillMaxHeight(), verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(
                        text = "收入",
                        style = TextStyle(
                            color = Color.White, fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Start,
                    )
                    Text(
                        text = "${if (monthTotalIncomings != null) monthTotalIncomings else 0.0}",
                        style = TextStyle(
                            color = Color.White, fontSize = 20.sp
                        ), modifier = Modifier.widthIn(50.dp, 200.dp)
                    )
                }
                Column(
                    Modifier
                        .fillMaxHeight(), verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "支出",
                        style = TextStyle(
                            color = Color.White, fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "${if (monthTotalOutgoings != null) monthTotalOutgoings else 0.0}",
                        style = TextStyle(
                            color = Color.White, fontSize = 20.sp
                        ),
                        modifier = Modifier
                            .widthIn(50.dp, 200.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }

        }

    }
}




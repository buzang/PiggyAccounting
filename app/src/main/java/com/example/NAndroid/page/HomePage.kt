package com.example.NAndroid.page

import ActionAdd
import BillTypeSelectionButtons
import android.annotation.SuppressLint
import android.os.Build
import android.provider.Telephony.Mms.Addr
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.AddHomeWork
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.AreaChart
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.PartyMode
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.NAndroid.ui.compose.BillList
import com.example.NAndroid.ui.compose.BillSubDescription
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.sp
import com.example.NAndroid.data.entities.Bill
import com.example.NAndroid.data.entities.BillCategory
import com.example.NAndroid.ui.compose.HomeTopBar
import com.example.NAndroid.ui.compose.ListSubDescription
import com.example.NAndroid.ui.compose.MyDateTimePicker
import com.example.NAndroid.utlis.DBHelper
import com.example.NAndroid.utlis.getDateStartTimeAndEndTimeByTimestamp
import com.example.NAndroid.utlis.timestampToYearOrMonth
import getMonthStartAndEndTimeByTimestamp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import java.util.Calendar


val currentDatetime = Calendar.getInstance().getTimeInMillis() / 1000


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    BottomSheetDemo()
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomSheetDemo() {

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var optionsList by remember { mutableStateOf(mutableListOf<BillCategory>()) }
    // 支出
    var Outgoings by remember { mutableStateOf(0.0) }
    // 月支付
    var MonthTotalOutgoings by remember { mutableStateOf(0.0) }
    // 收入
    var Incomings by remember { mutableStateOf(0.0) }
    // 月收入
    var MonthTotalIncomings by remember { mutableStateOf(0.0) }
    var billList by remember { mutableStateOf(mutableListOf<Bill>()) }
    var openDialog = remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    // 新增变动标记
    var addChangeTag by remember { mutableStateOf(false) }

    var currentTimestamp by remember { mutableStateOf<Long>(currentDatetime) }
    var year by remember { mutableStateOf(timestampToYearOrMonth(currentTimestamp, "Y")) }
    var month by remember { mutableStateOf(timestampToYearOrMonth(currentTimestamp, "M")) }
    var day by remember { mutableStateOf(timestampToYearOrMonth(currentTimestamp, "D")) }
    var week by remember { mutableStateOf(timestampToYearOrMonth(currentTimestamp, "W")) }

    LaunchedEffect(Unit) {
        initCategories(1, callback = { optionsList = it.toMutableList() })
    }

    LaunchedEffect(currentTimestamp, addChangeTag) {

        computeIncomingsAndOutgoings(currentTimestamp,
            { Incomings = it.first;Outgoings = it.second })

        computeMonthIncomingsAndOutgoings(currentTimestamp,
            { MonthTotalIncomings = it.first;MonthTotalOutgoings = it.second })

        getBillList(currentTimestamp, { billList = it.toMutableList() })
    }
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                var amount by remember { mutableStateOf("") }
                var remark by remember { mutableStateOf("") }
                var billType by remember { mutableStateOf(1) }
                var billActionType by remember { mutableStateOf(1) }


                Row() {
                    ActionAdd(billType, onOptionSelected = {
                        billType = it

                        initCategories(billType, callback = {
                            optionsList = it.toMutableList()
                        })

                    })
                    Row(
                        modifier = Modifier
                            .width(120.dp)
                            .height(40.dp)
                            .padding(top = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "📆", style = TextStyle(
                                fontSize = 25.sp,
                            ),
                            modifier = Modifier
                                .clickable(onClick = {
                                    openDialog.value = true
                                })
                        )

                        Text(
                            text = "$year/$month/$day",
                            modifier = Modifier
                                .widthIn(30.dp, 100.dp)
                                .padding(horizontal = 5.dp)
                                .padding(top = 2.dp),
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 14.sp,
                            )
                        )
                    }
                }


                BillTypeSelectionButtons(
                    optionsList,
                    billActionType,
                    callback = { billActionType = it })

                TextField(
                    value = amount,
                    label = { Text(text = "$") },
                    onValueChange = { newAmount ->
                        amount = newAmount
                    },
                    modifier = Modifier
                        .padding(12.dp)
                        .border(2.dp, Color(0xFF9C27B0), RoundedCornerShape(8.dp)),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        cursorColor = Color(0xFF9C27B0),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp),  // 圆角形状
                    singleLine = true,

                    placeholder = {
                        Text(
                            text = "请输入金额",
                            color = Color.Gray
                        )
                    },
                )

                TextField(
                    value = remark,
                    label = { Text(text = "🔖") },
                    onValueChange = { newAmount ->
                        remark = newAmount
                    },
                    modifier = Modifier
                        .padding(12.dp)
                        .border(2.dp, Color(0xFF9C27B0), RoundedCornerShape(8.dp)),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        cursorColor = Color(0xFF9C27B0),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,

                    placeholder = {
                        Text(
                            text = "请输入账单备注",
                            color = Color.Gray
                        )
                    },
                )


                // 使用 Modifier 来调整布局
                Button(
                    onClick = {
                        handleAddBill(
                            billActionType,
                            amount.toDouble(),
                            remark,
                            billType,
                            currentTimestamp,
                            success = {
                                addChangeTag = !addChangeTag
                            })

                        // 清空
                        amount = ""
                        remark = ""

                        coroutineScope.launch { sheetState.hide() }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(
                            155,
                            89,
                            182
                        ),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(topStart = 0.dp, bottomStart = 0.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .width(280.dp)
                ) {

                    Text(text = "提交")
                }

            }
        }
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            floatingActionButton = {

            }
        ) {
            HomeTopBar()

            Box(modifier = Modifier.fillMaxSize()) {


                BillSubDescription(
                    year,
                    month,
                    monthTotalIncomings = MonthTotalIncomings,
                    monthTotalOutgoings = MonthTotalOutgoings, openDialog
                )

                ActionCard()

                ListSubDescription("${month} 月 ${day} 日   $week", Incomings, Outgoings)

                BillList(billList, snackbarHostState) {
                    CoroutineScope(Dispatchers.Main).launch {
                        DBHelper.use().billDao().deleteBill(it)
                        addChangeTag = !addChangeTag

                    }
                }

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "🐷", style = TextStyle(fontSize = 23.sp), modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 15.dp)
                            .padding(horizontal = 20.dp)
                            .clickable(
                                onClick = {
                                    coroutineScope.launch { sheetState.show() }
                                },
                            )

                    )

                }
            }
        }
    }

    // 日期选择器
    if (openDialog.value) {
        MyDateTimePicker(openDialog, datePickerState) {
            openDialog.value = false

            currentTimestamp = datePickerState.selectedDateMillis!! / 1000

            year = timestampToYearOrMonth(currentTimestamp, "Y")
            month = timestampToYearOrMonth(currentTimestamp, "M")
            day = timestampToYearOrMonth(currentTimestamp, "D")
            week = timestampToYearOrMonth(currentTimestamp, "W")

            Log.i("############", currentTimestamp.toString())
        }
    }
}


@Composable
fun ActionCard() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(185.dp)
            .padding(top = 105.dp)
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(255, 255, 255)
        ),
    ) {
        // Card内容

        Row(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Text(
                text = "💰", style = TextStyle(
                    fontSize = 25.sp,
                )
            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Outlined.AreaChart, contentDescription = "33", Modifier.size(30.dp))
                Text(
                    text = "统计", style = TextStyle(
                        fontSize = 11.sp,
                        fontWeight = FontWeight(100)
                    )
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Outlined.AutoAwesome, contentDescription = "33", Modifier.size(30.dp))
                Text(
                    text = "盲盒", style = TextStyle(
                        fontSize = 11.sp,
                        fontWeight = FontWeight(100)
                    )
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Outlined.AccountBalanceWallet,
                    contentDescription = "33",
                    Modifier.size(30.dp)
                )
                Text(
                    text = "钱包", style = TextStyle(
                        fontSize = 11.sp,
                        fontWeight = FontWeight(100)
                    )
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Outlined.AdminPanelSettings,
                    contentDescription = "33",
                    Modifier.size(30.dp)
                )
                Text(
                    text = "设置", style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight(100)
                    )
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Outlined.Apps, contentDescription = "33", Modifier.size(30.dp))
                Text(
                    text = "更多", style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight(100)
                    )
                )
            }
        }
    }
}

// 获取今天的账单
fun getBillList(_currentDateTime: Long, callback: (List<Bill>) -> Unit) {

    Log.i("##########Time", _currentDateTime.toString())


    val (startTime, endTime) = getDateStartTimeAndEndTimeByTimestamp(_currentDateTime)

    // 1704729599
    // 1704643200
    // 1704692444
    Log.i("##########Time", "$startTime.toString() #### $endTime.toString()")

    CoroutineScope(Dispatchers.Main).launch {
        val result = withContext(Dispatchers.IO) {
            DBHelper.use().billDao().getBillsBetweenDates(startTime, endTime)
        }
        Log.i("##########", result.toString())
        if (result.isNotEmpty()) {
            callback(result)
        } else {
            callback(result)
        }
    }
}


// 计算今天的收入支出信息
fun computeIncomingsAndOutgoings(_currentDateTime: Long, callback: (Pair<Double, Double>) -> Unit) {
    val (startTime, endTime) = getDateStartTimeAndEndTimeByTimestamp(_currentDateTime)

    CoroutineScope(Dispatchers.Main).launch {
        val incomingsDeferred = async(Dispatchers.IO) {
            DBHelper.use().billDao().getIncomeSumBetweenDates(startTime, endTime) ?: 0.0
        }

        val outgoingsDeferred = async(Dispatchers.IO) {
            DBHelper.use().billDao().getExpenseSumBetweenDates(startTime, endTime) ?: 0.0
        }

        val incomings = incomingsDeferred.await()
        val outgoings = outgoingsDeferred.await()

        // 使用查询结果调用callback
        callback(Pair(incomings, outgoings))
    }
}

// 查询种类
fun initCategories(type: Int, callback: (List<BillCategory>) -> Unit) {
    // 使用CoroutineScope启动新的协程
    CoroutineScope(Dispatchers.IO).launch {
        // 确保DAO函数是挂起函数
        var categories = DBHelper.use().billCategoryDao().getBillCategoriesByType(type)
        if (categories.isNotEmpty()) {
            callback(categories)
        }
    }
}


// 计算本月的收入支出信息
fun computeMonthIncomingsAndOutgoings(
    _currentDateTime: Long,
    callback: (Pair<Double, Double>) -> Unit
) {
    val (startTime, endTime) = getMonthStartAndEndTimeByTimestamp(_currentDateTime)

    CoroutineScope(Dispatchers.Main).launch {
        val incomingsDeferred = async(Dispatchers.IO) {
            DBHelper.use().billDao().getIncomeSumBetweenDates(startTime, endTime) ?: 0.0
        }

        val outgoingsDeferred = async(Dispatchers.IO) {
            DBHelper.use().billDao().getExpenseSumBetweenDates(startTime, endTime) ?: 0.0
        }

        val incomings = incomingsDeferred.await()
        val outgoings = outgoingsDeferred.await()


        Log.i("###########TOTAL", "$incomings $outgoings")

        // 使用查询结果调用callback
        callback(Pair(incomings, outgoings))
    }
}

fun handleAddBill(
    categoryId: Int,
    amount: Double,
    remark: String,
    type: Int,
    _currentTimestamp: Long,
    success: () -> Unit
) {

    Log.v("####", "执行添加")
    var b = Bill(
        categoryId = categoryId,
        amount = amount,
        remark = remark,
        time = _currentTimestamp,
        type = type
    )

    CoroutineScope(Dispatchers.IO).launch {
        DBHelper.use().billDao().insertBill(b)
        // 之后在主线程上刷新列表
        withContext(Dispatchers.Main) {
            // 更新billList状态
            success()
        }
    }
}


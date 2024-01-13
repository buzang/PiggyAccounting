package com.example.NAndroid.utlis

import java.util.Calendar


/**
 * 传入一个时间戳 返回当天的结束日期
 */
fun getEndOfDayTimestamp(timestamp: Long): Long {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = timestamp
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        add(Calendar.DAY_OF_MONTH, 1)
        add(Calendar.MILLISECOND, -1)
    }
    return calendar.timeInMillis
}
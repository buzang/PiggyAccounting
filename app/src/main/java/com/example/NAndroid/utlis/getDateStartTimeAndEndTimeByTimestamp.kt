package com.example.NAndroid.utlis

import java.util.Calendar


/**
 * 根据传入的时间戳 返回当天的开始时间和结束时间
 */
fun getDateStartTimeAndEndTimeByTimestamp(timestamp: Long): Pair<Long, Long> {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = timestamp * 1000 // 转换为毫秒
    }

    val startOfDay: Long = calendar.apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis / 1000 // 转换为秒

    val endOfDay: Long = calendar.apply {
        add(Calendar.DAY_OF_MONTH, 1)
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        add(Calendar.SECOND, -1)
    }.timeInMillis / 1000 // 转换为秒

    return Pair(startOfDay, endOfDay)
}
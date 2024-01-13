package com.example.NAndroid.utlis

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.text.SimpleDateFormat
import java.util.*


/**
 * 格式化时间戳
 * @param timestamp 时间戳
 * @param specifier 说明符 Y 返回年份 M 返回月份 D 返回 日 W 返回星期几
 */
fun timestampToYearOrMonth(timestamp: Long?, specifier: String): String {
    val format = when (specifier) {
        "Y" -> "yyyy" // 如果传入的是"Y"，格式化为年份
        "M" -> "MM"   // 如果传入的是"M"，格式化为月份
        "D" -> "dd"   // 如果传入的是"D"，格式化为日
        "W" -> "EEEE" // 如果传入的是"W"，格式化为星期几
        else -> "yyyy/MM/dd" // 默认格式化为完整日期
    }

    if (timestamp == null) return ""
    val date = Date(timestamp * 1000)
    val dateFormat = SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE)
    return dateFormat.format(date)
}
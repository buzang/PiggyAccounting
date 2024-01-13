import java.util.Calendar

/**
 * 根据传入的时间戳 返回当月的开始时间和结束时间
 * @param timestamp 时间戳（秒）
 * @return 当月开始和结束时间的时间戳（秒）
 */
fun getMonthStartAndEndTimeByTimestamp(timestamp: Long): Pair<Long, Long> {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = timestamp * 1000 // 转换为毫秒
        set(Calendar.DAY_OF_MONTH, 1) // 设置为当前月的第一天
        set(Calendar.HOUR_OF_DAY, 0) // 设置小时为0
        set(Calendar.MINUTE, 0) // 设置分钟为0
        set(Calendar.SECOND, 0) // 设置秒为0
        set(Calendar.MILLISECOND, 0) // 设置毫秒为0
    }

    val startOfMonth: Long = calendar.timeInMillis / 1000 // 保存当月开始时间戳（秒）

    calendar.add(Calendar.MONTH, 1) // 移到下个月的第一天
    calendar.add(Calendar.SECOND, -1) // 然后减去1秒得到当前月的最后一秒

    val endOfMonth: Long = calendar.timeInMillis / 1000 // 保存当月结束时间戳（秒）

    return Pair(startOfMonth, endOfMonth)
}

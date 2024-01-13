package com.example.NAndroid.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(
        entity = BillCategory::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("category_id"),
        onDelete = ForeignKey.CASCADE
    )
])
data class Bill(
    // ID
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "category_id") val categoryId: Int,
    // 账单类型 收入 1 ，支出 2
     @ColumnInfo(name= "bill_type") val type:Int,
    // 金额
    @ColumnInfo(name = "amount") val amount: Double,
    // 时间
    @ColumnInfo(name = "time") val time: Long?,
    // 备注
    @ColumnInfo(name = "remark") val remark: String,
)
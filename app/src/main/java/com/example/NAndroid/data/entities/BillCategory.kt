package com.example.NAndroid.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class BillCategory(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    // 账单类型
    @ColumnInfo(name = "type") val type: Int,
    //账单分类
    @ColumnInfo(name = "category") val category: String,
    )


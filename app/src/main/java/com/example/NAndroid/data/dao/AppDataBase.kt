package com.example.NAndroid.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.NAndroid.data.entities.Bill
import com.example.NAndroid.data.entities.BillCategory
import com.example.NAndroid.data.entities.User
import com.example.NAndroid.data.entities.UserInfo

@Database(
    entities = [User::class, UserInfo::class, Bill::class, BillCategory::class],
    version = 6,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userInfoDao(): UserInfoDao
    abstract fun billDao(): BillDao
    abstract fun billCategoryDao(): BillCategoryDao
}


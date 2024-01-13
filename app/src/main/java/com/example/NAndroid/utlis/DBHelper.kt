package com.example.NAndroid.utlis

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.NAndroid.MyApp
import com.example.NAndroid.data.dao.AppDatabase

object DBHelper {
    // 使用 lateinit 声明，但需要在使用前确保已初始化
    lateinit var database: AppDatabase

    // 使用一个方法来初始化数据库
    fun init(application: Application) {
        if (!::database.isInitialized) {
            database = Room.databaseBuilder(
                application.applicationContext,
                AppDatabase::class.java, "database-name"
            ).fallbackToDestructiveMigration().build()
        }
    }

    fun use(): AppDatabase {
        // 在使用前检查是否已初始化
        if (!::database.isInitialized) {
            throw IllegalStateException("DBHelper must be initialized before use.")
        }
        return database
    }
}

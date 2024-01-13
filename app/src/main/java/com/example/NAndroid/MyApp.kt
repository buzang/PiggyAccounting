package com.example.NAndroid

import android.app.Application
import androidx.room.Room
import com.example.NAndroid.data.dao.AppDatabase
import com.example.NAndroid.utlis.DBHelper

class MyApp:Application() {

    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        DBHelper.init(this)

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }
}
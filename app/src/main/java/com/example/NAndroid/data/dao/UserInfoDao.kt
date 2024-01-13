package com.example.NAndroid.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.NAndroid.data.entities.UserInfo

@Dao
interface UserInfoDao {
    @Query("SELECT * FROM userinfo")
    fun getAll(): List<UserInfo>

    @Query("SELECT * FROM userinfo WHERE userId IN (:userIds)")
    fun loadAllByIds(userIds: Int): List<UserInfo>

    @Query("SELECT * FROM userinfo WHERE  password = :password LIMIT 1")
    fun login( password: String): UserInfo?

    @Insert
    fun insertAll(vararg users: UserInfo)
}


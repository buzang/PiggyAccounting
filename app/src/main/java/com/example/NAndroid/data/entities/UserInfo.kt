package com.example.NAndroid.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfo(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    @ColumnInfo(name = "user_name")val userName: String?,
    @ColumnInfo(name = "password")val password: String?,
    @ColumnInfo(name = "name")val name: String?,
    @ColumnInfo(name = "phone")val phone: String?
)

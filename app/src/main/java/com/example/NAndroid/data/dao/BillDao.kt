package com.example.NAndroid.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.NAndroid.data.entities.Bill
import com.example.NAndroid.data.entities.User

@Dao
interface BillDao {
    // 插入一条新的账单
    @Insert
    suspend fun insertBill(bill: Bill)

    // 插入多条账单
    @Insert
    suspend fun insertAll(vararg bills: Bill)

    // 更新账单
    @Update
    suspend fun updateBill(bill: Bill)

    // 根据ID删除账单
    @Query("DELETE FROM bill WHERE id = :id")
    suspend fun deleteBill(id: Int)

    // 查询所有账单
    @Query("SELECT * FROM bill")
    fun getAllBills(): List<Bill>

    // 查询特定类型的账单
    @Query("SELECT * FROM bill WHERE bill_type = :type")
    fun getBillsByType(type: Int): LiveData<List<Bill>>

    // 计算指定时间范围内所有收入的总和
    @Query("SELECT SUM(amount) FROM Bill WHERE bill_type = 0 AND time BETWEEN :startTime AND :endTime")
    fun getIncomeSumBetweenDates(startTime: Long, endTime: Long): Double

    // 计算指定时间范围内所有支出的总和
    @Query("SELECT SUM(amount) FROM Bill WHERE bill_type = 1 AND time BETWEEN :startTime AND :endTime")
    fun getExpenseSumBetweenDates(startTime: Long, endTime: Long): Double

    // 查询特定时间范围内的账单
    @Query("SELECT * FROM bill WHERE time BETWEEN :startTime AND :endTime")
    fun getBillsBetweenDates(startTime: Long, endTime: Long): List<Bill>

    // 查询所有收入账单
    @Query("SELECT * FROM bill WHERE bill_type = 1")
    fun getIncomeBills(): LiveData<List<Bill>>

    // 查询所有支出账单
    @Query("SELECT * FROM bill WHERE bill_type = 2")
    fun getExpenseBills(): LiveData<List<Bill>>
}
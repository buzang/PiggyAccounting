package com.example.NAndroid.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.NAndroid.data.entities.BillCategory


@Dao
interface BillCategoryDao {
    // 插入一条新的账单分类
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBillCategory(billCategory: BillCategory)

    // 插入多条账单分类
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBillCategories(vararg billCategories: BillCategory)

    // 更新账单分类
    @Update
    suspend fun updateBillCategory(billCategory: BillCategory)

    // 根据UID删除账单分类
    @Query("DELETE FROM BillCategory WHERE uid = :uid")
    suspend fun deleteBillCategory(uid: Int)

    // 查询所有账单分类
    @Query(value = "SELECT * FROM BillCategory")
    fun getAllBillCategories(): List<BillCategory>

    // 根据账单类型查询账单分类
    @Query("SELECT * FROM BillCategory WHERE type = :type")
    fun getBillCategoriesByType(type: Int): List<BillCategory>

    // 根据账单分类名称查询账单分类
    @Query("SELECT * FROM BillCategory WHERE category = :category")
    fun getBillCategoryByCategoryName(category: String): BillCategory
}
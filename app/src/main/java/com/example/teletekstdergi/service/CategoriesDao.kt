package com.example.teletekstdergi.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.teletekstdergi.model.Category

@Dao
interface CategoriesDao {

    @Insert
    suspend fun insertAllCategories(vararg categories: Category)

    @Query("SELECT * FROM categories")
    suspend fun getAllCategories(): List<Category>

    @Query("SELECT * FROM categories WHERE categoryId=:categoryId")
    suspend fun getCategory(categoryId: Int): Category

    @Query("DELETE FROM categories")
    suspend fun deleteAllCategories()
}
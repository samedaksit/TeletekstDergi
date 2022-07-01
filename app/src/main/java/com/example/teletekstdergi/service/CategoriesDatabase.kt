package com.example.teletekstdergi.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.teletekstdergi.model.Category
import com.example.teletekstdergi.model.Converter

@Database(entities = [Category::class], version = 1)
@TypeConverters(Converter::class)
abstract class CategoriesDatabase : RoomDatabase() {

    abstract fun categoriesDao(): CategoriesDao

    companion object {

        @Volatile
        private var instance: CategoriesDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, CategoriesDatabase::class.java, "categoriesDatabase"
        ).build()

    }

}
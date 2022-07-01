package com.example.teletekstdergi.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.teletekstdergi.model.*

@Database(
    entities = [Poem::class, Sport::class, Essay::class, Review::class, Story::class, ETeletekst::class, Psychology::class, Academy::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class ArticlesDatabase : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao

    companion object {

        @Volatile
        private var instance: ArticlesDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, ArticlesDatabase::class.java, "articlesDatabase"
        ).build()
    }
}
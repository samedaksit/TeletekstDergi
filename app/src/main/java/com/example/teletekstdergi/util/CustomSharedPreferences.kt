package com.example.teletekstdergi.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class CustomSharedPreferences {

    companion object {
        private const val CATEGORIES_LOADED_FROM_API = "are_categories_loaded_from_api"
        private const val ARTICLES_LOADED_FROM_API = "are_articles_loaded_from_api"
        private var sharedPreferences: SharedPreferences? = null

        @Volatile
        private var instance: CustomSharedPreferences? = null
        private val lock = Any()

        operator fun invoke(context: Context): CustomSharedPreferences =
            instance ?: synchronized(lock) {
                instance ?: makeCustomSharedPreferences(context).also {
                    instance = it
                }
            }

        private fun makeCustomSharedPreferences(context: Context): CustomSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }
    }

    fun areCategoriesLoadedFromAPI(isLoaded: Boolean) {
        sharedPreferences?.edit(commit = true) {
            putBoolean(CATEGORIES_LOADED_FROM_API, isLoaded)
        }
    }

    fun areArticlesLoadedFromAPI(isLoaded: Boolean) {
        sharedPreferences?.edit(commit = true) {
            putBoolean(ARTICLES_LOADED_FROM_API, isLoaded)
        }
    }

    fun checkIfCategoriesLoadedFromAPI() =
        sharedPreferences?.getBoolean(CATEGORIES_LOADED_FROM_API, false)

    fun checkIfArticlesLoadedFromAPI() =
        sharedPreferences?.getBoolean(ARTICLES_LOADED_FROM_API, false)
}
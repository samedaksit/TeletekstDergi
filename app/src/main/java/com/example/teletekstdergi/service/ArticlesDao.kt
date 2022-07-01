package com.example.teletekstdergi.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.teletekstdergi.model.*

@Dao
interface ArticlesDao {

    @Insert
    suspend fun insertAllPoems(vararg poem: Poem)

    @Query("SELECT * FROM poems ")
    suspend fun getPoems(): List<Poem>

    @Query("DELETE FROM poems")
    suspend fun deleteAllPoems()


    @Insert
    suspend fun insertAllSports(vararg sport: Sport)

    @Query("SELECT * FROM sports ")
    suspend fun getSports(): List<Sport>

    @Query("DELETE FROM sports")
    suspend fun deleteAllSports()


    @Insert
    suspend fun insertAllEssays(vararg essay: Essay)

    @Query("SELECT * FROM essays ")
    suspend fun getEssays(): List<Essay>

    @Query("DELETE FROM essays")
    suspend fun deleteAllEssays()


    @Insert
    suspend fun insertAllReviews(vararg review: Review)

    @Query("SELECT * FROM reviews ")
    suspend fun getReviews(): List<Review>

    @Query("DELETE FROM reviews")
    suspend fun deleteAllReviews()


    @Insert
    suspend fun insertAllStories(vararg story: Story)

    @Query("SELECT * FROM stories ")
    suspend fun getStories(): List<Story>

    @Query("DELETE FROM stories")
    suspend fun deleteAllStories()


    @Insert
    suspend fun insertAllETeletekst(vararg eTeletekst: ETeletekst)

    @Query("SELECT * FROM eteletekst ")
    suspend fun getETeletekst(): List<ETeletekst>

    @Query("DELETE FROM eteletekst")
    suspend fun deleteAllETeletekst()


    @Insert
    suspend fun insertAllPsychology(vararg psychology: Psychology)

    @Query("SELECT * FROM psychology ")
    suspend fun getPsychology(): List<Psychology>

    @Query("DELETE FROM psychology")
    suspend fun deleteAllPsychology()


    @Insert
    suspend fun insertAllAcademy(vararg academy: Academy)

    @Query("SELECT * FROM academies ")
    suspend fun getAcademy(): List<Academy>

    @Query("DELETE FROM academies")
    suspend fun deleteAllAcademy()
}
package com.example.teletekstdergi.service

import com.example.teletekstdergi.model.*
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET

interface ArticlesAPI {

    @GET("posts?categories=1&per_page=100")
    fun getPoem(): Single<List<Poem>>

    @GET("posts?categories=10&per_page=100")
    fun getSport(): Single<List<Sport>>

    @GET("posts?categories=11&per_page=100")
    fun getEssay(): Single<List<Essay>>

    @GET("posts?categories=13&per_page=100")
    fun getReview(): Single<List<Review>>

    @GET("posts?categories=14&per_page=100")
    fun getStory(): Single<List<Story>>

    @GET("posts?categories=22&per_page=100")
    fun getETeletekst(): Single<List<ETeletekst>>

    @GET("posts?categories=28&per_page=100")
    fun getPsychology(): Single<List<Psychology>>

    @GET("posts?categories=30&per_page=100")
    fun getAcademy(): Single<List<Academy>>


}
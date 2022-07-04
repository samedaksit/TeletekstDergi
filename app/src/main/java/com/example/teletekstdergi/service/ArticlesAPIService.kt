package com.example.teletekstdergi.service

import com.example.teletekstdergi.model.*
import com.example.teletekstdergi.model.Constants.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ArticlesAPIService {
    private val api = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ArticlesAPI::class.java)

    fun getPoem(): Single<List<Poem>> {
        return api.getPoem()
    }

    fun getSport(): Single<List<Sport>> {
        return api.getSport()
    }

    fun getEssay(): Single<List<Essay>> {
        return api.getEssay()
    }

    fun getReview(): Single<List<Review>> {
        return api.getReview()
    }

    fun getStory(): Single<List<Story>> {
        return api.getStory()
    }

    fun getETeletekst(): Single<List<ETeletekst>> {
        return api.getETeletekst()
    }

    fun getPsychology(): Single<List<Psychology>> {
        return api.getPsychology()
    }

    fun getAcademy(): Single<List<Academy>> {
        return api.getAcademy()
    }
}
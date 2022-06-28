package com.example.teletekstdergi.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.teletekstdergi.model.Category
import com.example.teletekstdergi.service.CategoriesAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CategoriesViewModel(application: Application) : BaseViewModel(application) {

    private val categoriesAPIService = CategoriesAPIService()
    private val disposable = CompositeDisposable()

    val categories = MutableLiveData<List<Category>>()

    fun getCategoriesFromAPI() {
        println("salih getfromapi")

        disposable.add(
            categoriesAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Category>>() {
                    override fun onSuccess(t: List<Category>) {
                        categories.value = t
                        println("salih başarılı")
                    }

                    override fun onError(e: Throwable) {
                        println("salih başarısız")
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
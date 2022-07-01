package com.example.teletekstdergi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.teletekstdergi.model.*
import com.example.teletekstdergi.service.ArticlesAPIService
import com.example.teletekstdergi.service.ArticlesDatabase
import com.example.teletekstdergi.service.CategoriesAPIService
import com.example.teletekstdergi.service.CategoriesDatabase
import com.example.teletekstdergi.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class CategoriesViewModel(application: Application) : BaseViewModel(application) {

    private val categoriesAPIService = CategoriesAPIService()
    private val articlesAPIService = ArticlesAPIService()
    private val disposable = CompositeDisposable()
    private val customPreferences = CustomSharedPreferences(getApplication())

    val categories = MutableLiveData<List<Category>>()
    val articles = MutableLiveData<List<Article>>()

    fun getCategories() {
        val isLoaded = customPreferences.checkIfCategoriesLoadedFromAPI()
        if (isLoaded == true) {
            getCategoriesFromSqlite()
        } else {
            getCategoriesFromAPI()
        }
    }

    private fun getCategoriesFromSqlite() {
        launch {
            val categoryList =
                CategoriesDatabase(getApplication()).categoriesDao().getAllCategories()
            showCategories(categoryList)
        }
    }

    private fun getCategoriesFromAPI() {
        disposable.add(
            categoriesAPIService.getCategories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Category>>() {
                    override fun onSuccess(t: List<Category>) {
                        storeCategoriesInSqlite(t)
                        customPreferences.areCategoriesLoadedFromAPI(true)
                    }

                    override fun onError(e: Throwable) {
                    }

                })
        )
    }

    fun getArticles(categoryId: Int) {
        val isLoaded = customPreferences.checkIfArticlesLoadedFromAPI()
        if (isLoaded == true) {
            getArticlesFromSqlite(categoryId)
            Toast.makeText(getApplication(), "Data refreshed from SQLite", Toast.LENGTH_LONG).show()
        } else {
            getArticlesFromAPI(categoryId)
            Toast.makeText(getApplication(), "Data refreshed from API", Toast.LENGTH_LONG).show()
        }

    }

    fun refreshArticles(categoryId: Int) {
        getArticlesFromAPI(categoryId)
    }

    private fun getArticlesFromSqlite(categoryId: Int) {
        when (categoryId) {
            1 -> {
                launch {
                    val list = ArticlesDatabase(getApplication()).articlesDao().getPoems()
                    val articleList = list.map {
                        Article(it.id, it.date, it.headJson, it.content)
                    }
                    showArticles(articleList)
                }
            }
            10 -> {
                launch {
                    val list = ArticlesDatabase(getApplication()).articlesDao().getSports()
                    val articleList = list.map {
                        Article(it.id, it.date, it.headJson, it.content)
                    }
                    showArticles(articleList)
                }
            }
            11 -> {
                launch {
                    val list = ArticlesDatabase(getApplication()).articlesDao().getEssays()
                    val articleList = list.map {
                        Article(it.id, it.date, it.headJson, it.content)
                    }
                    showArticles(articleList)
                }
            }
            13 -> {
                launch {
                    val list = ArticlesDatabase(getApplication()).articlesDao().getReviews()
                    val articleList = list.map {
                        Article(it.id, it.date, it.headJson, it.content)
                    }
                    showArticles(articleList)
                }
            }
            14 -> {
                launch {
                    val list = ArticlesDatabase(getApplication()).articlesDao().getStories()
                    val articleList = list.map {
                        Article(it.id, it.date, it.headJson, it.content)
                    }
                    showArticles(articleList)
                }
            }
            22 -> {
                launch {
                    val list = ArticlesDatabase(getApplication()).articlesDao().getETeletekst()
                    val articleList = list.map {
                        Article(it.id, it.date, it.headJson, it.content)
                    }
                    showArticles(articleList)
                }
            }
            28 -> {
                launch {
                    val list = ArticlesDatabase(getApplication()).articlesDao().getPsychology()
                    val articleList = list.map {
                        Article(it.id, it.date, it.headJson, it.content)
                    }
                    showArticles(articleList)
                }
            }
            30 -> {
                launch {
                    val list = ArticlesDatabase(getApplication()).articlesDao().getAcademy()
                    val articleList = list.map {
                        Article(it.id, it.date, it.headJson, it.content)
                    }
                    showArticles(articleList)
                }
            }
        }
    }

    private fun getArticlesFromAPI(selectedCategory: Int) {
        disposable.addAll(

            articlesAPIService.getPoem()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Poem>>() {
                    override fun onSuccess(t: List<Poem>) {
                        storeArticlesInSqlite(categoryId = 1, poemList = t)
                        customPreferences.areArticlesLoadedFromAPI(true)
                        if (selectedCategory == 1) {
                            val articleList = t.map {
                                Article(it.id, it.date, it.headJson, it.content)
                            }
                            showArticles(articleList)
                        }
                    }

                    override fun onError(e: Throwable) {
                    }
                }),
            articlesAPIService.getSport()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Sport>>() {
                    override fun onSuccess(t: List<Sport>) {
                        storeArticlesInSqlite(categoryId = 10, sportList = t)
                        if (selectedCategory == 10) {
                            val articleList = t.map {
                                Article(it.id, it.date, it.headJson, it.content)
                            }
                            showArticles(articleList)
                        }
                    }

                    override fun onError(e: Throwable) {
                    }
                }),
            articlesAPIService.getEssay()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Essay>>() {
                    override fun onSuccess(t: List<Essay>) {
                        storeArticlesInSqlite(categoryId = 11, essayList = t)
                        if (selectedCategory == 11) {
                            val articleList = t.map {
                                Article(it.id, it.date, it.headJson, it.content)
                            }
                            showArticles(articleList)
                        }
                    }

                    override fun onError(e: Throwable) {
                    }
                }),
            articlesAPIService.getReview()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Review>>() {
                    override fun onSuccess(t: List<Review>) {
                        storeArticlesInSqlite(categoryId = 13, reviewList = t)
                        if (selectedCategory == 13) {
                            val articleList = t.map {
                                Article(it.id, it.date, it.headJson, it.content)
                            }
                            showArticles(articleList)
                        }
                    }

                    override fun onError(e: Throwable) {
                    }
                }),
            articlesAPIService.getStory()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Story>>() {
                    override fun onSuccess(t: List<Story>) {
                        storeArticlesInSqlite(categoryId = 14, storyList = t)
                        if (selectedCategory == 14) {
                            val articleList = t.map {
                                Article(it.id, it.date, it.headJson, it.content)
                            }
                            showArticles(articleList)
                        }
                    }

                    override fun onError(e: Throwable) {
                    }
                }),
            articlesAPIService.getETeletekst()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<ETeletekst>>() {
                    override fun onSuccess(t: List<ETeletekst>) {
                        storeArticlesInSqlite(categoryId = 22, eTeletekstList = t)
                        if (selectedCategory == 22) {
                            val articleList = t.map {
                                Article(it.id, it.date, it.headJson, it.content)
                            }
                            showArticles(articleList)
                        }
                    }

                    override fun onError(e: Throwable) {
                    }
                }),
            articlesAPIService.getPsychology()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Psychology>>() {
                    override fun onSuccess(t: List<Psychology>) {
                        storeArticlesInSqlite(categoryId = 28, psychologyList = t)
                        if (selectedCategory == 28) {
                            val articleList = t.map {
                                Article(it.id, it.date, it.headJson, it.content)
                            }
                            showArticles(articleList)
                        }
                    }

                    override fun onError(e: Throwable) {
                    }
                }),
            articlesAPIService.getAcademy()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Academy>>() {
                    override fun onSuccess(t: List<Academy>) {
                        storeArticlesInSqlite(categoryId = 30, academyList = t)
                        if (selectedCategory == 30) {
                            val articleList = t.map {
                                Article(it.id, it.date, it.headJson, it.content)
                            }
                            showArticles(articleList)
                        }
                    }

                    override fun onError(e: Throwable) {
                    }
                })
        )

    }

    private fun storeArticlesInSqlite(
        categoryId: Int,
        poemList: List<Poem>? = null,
        sportList: List<Sport>? = null,
        essayList: List<Essay>? = null,
        reviewList: List<Review>? = null,
        storyList: List<Story>? = null,
        eTeletekstList: List<ETeletekst>? = null,
        psychologyList: List<Psychology>? = null,
        academyList: List<Academy>? = null
    ) {
        when (categoryId) {
            1 -> {
                launch {
                    val dao = ArticlesDatabase(getApplication()).articlesDao()
                    dao.deleteAllPoems()
                    dao.insertAllPoems(*poemList!!.toTypedArray())
                }
            }
            10 -> {
                launch {
                    val dao = ArticlesDatabase(getApplication()).articlesDao()
                    dao.deleteAllSports()
                    dao.insertAllSports(*sportList!!.toTypedArray())
                }
            }
            11 -> {
                launch {
                    val dao = ArticlesDatabase(getApplication()).articlesDao()
                    dao.deleteAllEssays()
                    dao.insertAllEssays(*essayList!!.toTypedArray())
                }
            }
            13 -> {
                launch {
                    val dao = ArticlesDatabase(getApplication()).articlesDao()
                    dao.deleteAllReviews()
                    dao.insertAllReviews(*reviewList!!.toTypedArray())
                }
            }
            14 -> {
                launch {
                    val dao = ArticlesDatabase(getApplication()).articlesDao()
                    dao.deleteAllStories()
                    dao.insertAllStories(*storyList!!.toTypedArray())
                }
            }
            22 -> {
                launch {
                    val dao = ArticlesDatabase(getApplication()).articlesDao()
                    dao.deleteAllETeletekst()
                    dao.insertAllETeletekst(*eTeletekstList!!.toTypedArray())
                }
            }
            28 -> {
                launch {
                    val dao = ArticlesDatabase(getApplication()).articlesDao()
                    dao.deleteAllPsychology()
                    dao.insertAllPsychology(*psychologyList!!.toTypedArray())
                }
            }
            30 -> {
                launch {
                    val dao = ArticlesDatabase(getApplication()).articlesDao()
                    dao.deleteAllAcademy()
                    dao.insertAllAcademy(*academyList!!.toTypedArray())
                }
            }
        }

    }

    private fun storeCategoriesInSqlite(list: List<Category>) {
        launch {
            val dao = CategoriesDatabase(getApplication()).categoriesDao()
            dao.deleteAllCategories()
            dao.insertAllCategories(*list.toTypedArray())

            showCategories(list)
        }
    }

    private fun showCategories(categoryList: List<Category>) {
        categories.value = categoryList
    }

    private fun showArticles(articleList: List<Article>) {
        articles.value = articleList
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
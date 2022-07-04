package com.example.teletekstdergi.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.teletekstdergi.R
import com.example.teletekstdergi.model.ArticleContent
import com.example.teletekstdergi.service.ArticlesDatabase
import kotlinx.coroutines.launch

class ArticleContentViewModel(application: Application) : BaseViewModel(application) {

    val articleLiveData = MutableLiveData<ArticleContent>()

    fun getArticleFromRoom(articleId: Int, categoryId: Int, context: Context) {
        launch {
            val dao = ArticlesDatabase(getApplication()).articlesDao()
            when (categoryId) {
                1 -> {
                    val article = dao.getPoemWithId(articleId)
                    articleLiveData.value =
                        ArticleContent(
                            article.date?.substringBefore("T").toString(),
                            article.headJson?.author?.author.toString(),
                            context.getString(R.string.category_poem),
                            article.headJson?.title?.substringBefore("*").toString(),
                            article.headJson?.image?.first()?.imageUrl.toString(),
                            article.content?.rendered.toString()
                        )
                }
                10 -> {
                    val article = dao.getSportWithId(articleId)
                    articleLiveData.value =
                        ArticleContent(
                            article.date?.substringBefore("T").toString(),
                            article.headJson?.author?.author.toString(),
                            context.getString(R.string.category_sport),
                            article.headJson?.title?.substringBefore("*").toString(),
                            article.headJson?.image?.first()?.imageUrl.toString(),
                            article.content?.rendered.toString()
                        )
                }
                11 -> {
                    val article = dao.getEssayWithId(articleId)
                    articleLiveData.value =
                        ArticleContent(
                            article.date?.substringBefore("T").toString(),
                            article.headJson?.author?.author.toString(),
                            context.getString(R.string.category_essay),
                            article.headJson?.title?.substringBefore("*").toString(),
                            article.headJson?.image?.first()?.imageUrl.toString(),
                            article.content?.rendered.toString()
                        )
                }
                13 -> {
                    val article = dao.getReviewWithId(articleId)
                    articleLiveData.value =
                        ArticleContent(
                            article.date?.substringBefore("T").toString(),
                            article.headJson?.author?.author.toString(),
                            context.getString(R.string.category_review),
                            article.headJson?.title?.substringBefore("*").toString(),
                            article.headJson?.image?.first()?.imageUrl.toString(),
                            article.content?.rendered.toString()
                        )
                }
                14 -> {
                    val article = dao.getStoryWithId(articleId)
                    articleLiveData.value =
                        ArticleContent(
                            article.date?.substringBefore("T").toString(),
                            article.headJson?.author?.author.toString(),
                            context.getString(R.string.category_story),
                            article.headJson?.title?.substringBefore("*").toString(),
                            article.headJson?.image?.first()?.imageUrl.toString(),
                            article.content?.rendered.toString()
                        )
                }
                22 -> {
                    val article = dao.getETeletekstWithId(articleId)
                    articleLiveData.value =
                        ArticleContent(
                            article.date?.substringBefore("T").toString(),
                            article.headJson?.author?.author.toString(),
                            context.getString(R.string.category_e_teletekst),
                            article.headJson?.title?.substringBefore("*").toString(),
                            article.headJson?.image?.first()?.imageUrl.toString(),
                            article.content?.rendered.toString()
                        )
                }
                28 -> {
                    val article = dao.getPsychologyWithId(articleId)
                    articleLiveData.value =
                        ArticleContent(
                            article.date?.substringBefore("T").toString(),
                            article.headJson?.author?.author.toString(),
                            context.getString(R.string.category_psychology),
                            article.headJson?.title?.substringBefore("*").toString(),
                            article.headJson?.image?.first()?.imageUrl.toString(),
                            article.content?.rendered.toString()
                        )
                }
                30 -> {
                    val article = dao.getAcademyWithId(articleId)
                    articleLiveData.value =
                        ArticleContent(
                            article.date?.substringBefore("T").toString(),
                            article.headJson?.author?.author.toString(),
                            context.getString(R.string.category_academy),
                            article.headJson?.title?.substringBefore("*").toString(),
                            article.headJson?.image?.first()?.imageUrl.toString(),
                            article.content?.rendered.toString()
                        )
                }
            }

        }
    }
}
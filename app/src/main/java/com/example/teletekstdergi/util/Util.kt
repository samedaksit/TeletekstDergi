package com.example.teletekstdergi.util

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.teletekstdergi.R
import retrofit2.Retrofit

fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

fun placeHolderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8F
        centerRadius = 40F
        start()
    }
}

@BindingAdapter("android:download_url")
fun downloadImage(view: ImageView, url: String?) {
    view.downloadFromUrl(url, placeHolderProgressBar(view.context))
}

@BindingAdapter("android:formatted_date")
fun formatDate(view: TextView, text: String?) {
    view.text = text?.substringBefore("T").toString()
}

@BindingAdapter("android:formatted_title")
fun formatTitle(view: TextView, text: String?) {
    view.text = text?.substringBefore("*")?.uppercase()
}
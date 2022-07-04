package com.example.teletekstdergi.util

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.teletekstdergi.R

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

@SuppressLint("SetJavaScriptEnabled")
@BindingAdapter("android:load_url")
fun getWebView(view: WebView, url: String?) {

    view.settings.javaScriptEnabled = true
    view.settings.useWideViewPort = true
    view.settings.loadWithOverviewMode = true
    view.settings.setSupportZoom(true)
    view.settings.builtInZoomControls = true
    view.settings.displayZoomControls = false
    view.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
    view.settings.defaultFontSize = 40
    view.settings.defaultTextEncodingName = "utf-8"

    view.loadDataWithBaseURL(
        null,
        "<html><body>$url</body></html>",
        "text/html",
        "UTF-8",
        null
    )
}
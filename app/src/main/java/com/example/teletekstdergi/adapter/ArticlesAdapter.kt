package com.example.teletekstdergi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.teletekstdergi.R
import com.example.teletekstdergi.databinding.ItemArticleBinding
import com.example.teletekstdergi.model.Article

class ArticlesAdapter(private val articleList: ArrayList<Article>) :
    RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>() {

    class ArticlesViewHolder(var view: ItemArticleBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemArticleBinding>(
            inflater,
            R.layout.item_article,
            parent,
            false
        )

        return ArticlesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.view.article = articleList[position]
        holder.view.image = articleList[position].headJson?.image?.first()?.imageUrl.toString()
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    fun updateArticleList(newList: List<Article>) {
        articleList.clear()
        articleList.addAll(newList)
        notifyDataSetChanged()
    }
}
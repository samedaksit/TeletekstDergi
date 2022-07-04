package com.example.teletekstdergi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.teletekstdergi.R
import com.example.teletekstdergi.databinding.ItemArticleBinding
import com.example.teletekstdergi.model.Article
import com.example.teletekstdergi.view.CategoriesFragmentDirections

class ArticlesAdapter(private val articleList: ArrayList<Article>) :
    RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>(), ArticlesClickListener {

    private var categoryId = 1

    class ArticlesViewHolder(var view: ItemArticleBinding) : RecyclerView.ViewHolder(view.root)

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
        holder.view.listener = this
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    fun updateArticleList(newList: List<Article>) {
        articleList.clear()
        articleList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onArticleClicked(v: View) {
        val articleId = v.findViewById<TextView>(R.id.articleId).text.toString().toInt()
        val action = CategoriesFragmentDirections.actionCategoriesFragmentToArticleContentFragment()
            .setArticleId(articleId).setCategoryId(categoryId)

        Navigation.findNavController(v).navigate(action)
    }

    fun setCategoryId(id: Int) {
        categoryId = id
    }
}
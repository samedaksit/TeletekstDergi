package com.example.teletekstdergi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teletekstdergi.adapter.ArticlesAdapter
import com.example.teletekstdergi.adapter.CategoriesAdapter
import com.example.teletekstdergi.databinding.FragmentCategoryBinding
import com.example.teletekstdergi.viewmodel.CategoriesViewModel


class CategoriesFragment : Fragment(), CategoriesAdapter.OnItemClickListener {

    private lateinit var dataBinding: FragmentCategoryBinding

    private lateinit var categoriesViewModel: CategoriesViewModel

    private val categoriesAdapter = CategoriesAdapter(arrayListOf(), this)
    private val articlesAdapter = ArticlesAdapter(arrayListOf())

    private var selectedCategory = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentCategoryBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoriesViewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        categoriesViewModel.getCategories()
        categoriesViewModel.refreshArticles(1)

        setAdapters()
        setSwipeRefresh()

        observeCategories()
        observeArticles()

    }

    private fun setSwipeRefresh() {
        dataBinding.swipeRefresher.setOnRefreshListener {
            categoriesViewModel.refreshArticles(selectedCategory)
            dataBinding.swipeRefresher.isRefreshing = false
        }

    }

    private fun setAdapters() {
        dataBinding.categoriesRV.adapter = categoriesAdapter
        dataBinding.articlesRV.adapter = articlesAdapter
    }

    private fun observeCategories() {
        categoriesViewModel.categories.observe(viewLifecycleOwner) { categories ->
            categories?.let {
                categoriesAdapter.updateCountryList(categories)
            }
        }
    }

    private fun observeArticles() {
        categoriesViewModel.articles.observe(viewLifecycleOwner) { articles ->
            articles?.let {
                articlesAdapter.updateArticleList(articles)
            }
        }
    }

    override fun onItemClick(position: Int) {
        categoriesViewModel.getArticles(position)
        selectedCategory = position
    }

}
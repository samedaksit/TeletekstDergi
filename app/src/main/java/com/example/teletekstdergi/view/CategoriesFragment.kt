package com.example.teletekstdergi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.teletekstdergi.R
import com.example.teletekstdergi.adapter.ArticlesAdapter
import com.example.teletekstdergi.adapter.CategoriesAdapter
import com.example.teletekstdergi.databinding.FragmentCategoryBinding
import com.example.teletekstdergi.util.CustomArticleDividerItemDecoration
import com.example.teletekstdergi.util.CustomCategoryDividerItemDecoration
import com.example.teletekstdergi.viewmodel.CategoriesViewModel


class CategoriesFragment : Fragment(), CategoriesAdapter.OnItemClickListener {

    private lateinit var dataBinding: FragmentCategoryBinding

    private lateinit var categoriesViewModel: CategoriesViewModel

    private val categoriesAdapter = CategoriesAdapter(arrayListOf(), this)
    private val articlesAdapter = ArticlesAdapter(arrayListOf())

    private var selectedCategory = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentCategoryBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.fragmentCategoryToolbar.title = "Teletekst Dergi"

        setArticlesRecyclerView()
        setCategoriesRecyclerView()
        setAdapters()
        setSwipeRefresh()

        observeCategories()

        observeIfArticleExists()
        observeArticleLoading()
        observeArticles()

    }

    private fun setViewModel() {
        categoriesViewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        categoriesViewModel.getCategories()
        categoriesViewModel.refreshArticles(1)
    }

    private fun setSwipeRefresh() {
        dataBinding.swipeRefresher.setOnRefreshListener {
            categoriesViewModel.refreshArticles(selectedCategory)
            dataBinding.swipeRefresher.isRefreshing = false
        }
    }

    private fun setCategoriesRecyclerView() {
        val widthInPixels = resources.getDimensionPixelSize(R.dimen.list_item_divider_width)
        context?.let {
            dataBinding.categoriesRV.addItemDecoration(
                CustomCategoryDividerItemDecoration(
                    ContextCompat.getColor(
                        it, R.color.category_divider_color
                    ),
                    widthInPixels
                )
            )
        }
    }

    private fun setArticlesRecyclerView() {
        val heightInPixels = resources.getDimensionPixelSize(R.dimen.list_item_divider_height)
        context?.let {
            dataBinding.articlesRV.addItemDecoration(
                CustomArticleDividerItemDecoration(
                    ContextCompat.getColor(
                        it, R.color.black
                    ),
                    heightInPixels
                )
            )
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

    private fun observeIfArticleExists() {
        categoriesViewModel.isArticleExists.observe(viewLifecycleOwner) { content ->
            content?.let {
                if (content) {
                    dataBinding.articlesRV.visibility = View.VISIBLE
                    dataBinding.noContentText.visibility = View.GONE
                } else {
                    dataBinding.noContentText.visibility = View.VISIBLE
                    dataBinding.articlesRV.visibility = View.GONE
                }
            }
        }
    }

    private fun observeArticleLoading() {
        categoriesViewModel.articleLoading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                if (loading) {
                    dataBinding.articleLoading.visibility = View.VISIBLE
                    dataBinding.noContentText.visibility = View.GONE
                    dataBinding.articlesRV.visibility = View.GONE
                } else {
                    dataBinding.articleLoading.visibility = View.GONE
                }
            }
        }
    }

    private fun observeArticles() {
        categoriesViewModel.articles.observe(viewLifecycleOwner) { articles ->
            articles?.let {
                articlesAdapter.updateArticleList(articles)
                dataBinding.articlesRV.scrollToPosition(0)
            }
        }
    }

    override fun onItemClick(position: Int) {
        if (position!=selectedCategory){
            categoriesViewModel.getArticles(position)
            selectedCategory = position
        }
        articlesAdapter.setCategoryId(selectedCategory)
    }
}
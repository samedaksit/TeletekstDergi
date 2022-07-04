package com.example.teletekstdergi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.teletekstdergi.R
import com.example.teletekstdergi.databinding.FragmentArticleContentBinding
import com.example.teletekstdergi.util.CustomSharedPreferences
import com.example.teletekstdergi.viewmodel.ArticleContentViewModel


class ArticleContentFragment : Fragment() {

    private lateinit var dataBinding: FragmentArticleContentBinding

    private lateinit var viewModel: ArticleContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentArticleContentBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(dataBinding.fragmentArticleContentToolBar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)


        viewModel = ViewModelProvider(this)[ArticleContentViewModel::class.java]

        arguments?.let {
            val articleId = ArticleContentFragmentArgs.fromBundle(it).articleId
            val categoryId = ArticleContentFragmentArgs.fromBundle(it).categoryId

            viewModel.getArticleFromRoom(articleId, categoryId, requireContext())
        }

        observeArticleLiveData()
    }

    private fun observeArticleLiveData() {
        viewModel.articleLiveData.observe(viewLifecycleOwner) { article ->
            article?.let {
                dataBinding.article = article
                dataBinding.fragmentArticleContentToolBar.title = article.articleTitle
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (requireActivity() as AppCompatActivity).setSupportActionBar(null)
    }

}
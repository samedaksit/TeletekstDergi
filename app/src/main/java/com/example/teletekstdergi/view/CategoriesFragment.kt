package com.example.teletekstdergi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teletekstdergi.R
import com.example.teletekstdergi.adapter.CategoriesAdapter
import com.example.teletekstdergi.databinding.FragmentCategoryBinding
import com.example.teletekstdergi.viewmodel.CategoriesViewModel


class CategoriesFragment : Fragment() {

    private lateinit var dataBinding: FragmentCategoryBinding

    private lateinit var categoriesViewModel: CategoriesViewModel

    private val categoriesAdapter = CategoriesAdapter(arrayListOf())

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
        categoriesViewModel.getCategoriesFromAPI()

        dataBinding.categoriesRV.layoutManager = LinearLayoutManager(context)
        dataBinding.categoriesRV.adapter = categoriesAdapter

        categoriesViewModel.categories.observe(viewLifecycleOwner) { categories ->
            categories?.let {
                categoriesAdapter.updateCountryList(categories)
                println("salih observe")
            }
        }

    }

}
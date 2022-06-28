package com.example.teletekstdergi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.teletekstdergi.R
import com.example.teletekstdergi.databinding.ItemCategoryBinding
import com.example.teletekstdergi.model.Category

class CategoriesAdapter(private val categoryList: ArrayList<Category>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    class CategoriesViewHolder(var view: ItemCategoryBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemCategoryBinding>(
            inflater,
            R.layout.item_category,
            parent,
            false
        )
        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.view.category = categoryList[position]
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun updateCountryList(newList: List<Category>) {
        categoryList.clear()
        categoryList.addAll(newList)
        notifyDataSetChanged()

    }
}
package com.example.teletekstdergi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.teletekstdergi.R
import com.example.teletekstdergi.databinding.ItemCategoryBinding
import com.example.teletekstdergi.model.Category

class CategoriesAdapter(
    private val categoryList: ArrayList<Category>,
    val listener: OnItemClickListener
) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    inner class CategoriesViewHolder(var view: ItemCategoryBinding) :
        RecyclerView.ViewHolder(view.root), View.OnClickListener {

        init {
            view.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            categoryList[position].categoryId?.let { listener.onItemClick(it) }
            categoryList.forEach {
                it.isClicked = false
            }
            categoryList[position].isClicked = true
            notifyDataSetChanged()
        }
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
        val sortedList = newList.sortedBy { q -> q.categoryId }
        categoryList.addAll(sortedList)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}
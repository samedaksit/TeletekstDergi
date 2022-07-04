package com.example.teletekstdergi.util

import android.graphics.Canvas
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView

class CustomCategoryDividerItemDecoration(color: Int, private val heightInPixels: Int) :
    RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init {
        paint.color = color
        paint.isAntiAlias = true
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val bottom = parent.top
        val top = parent.height
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + params.rightMargin
            val right = left + heightInPixels
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }
    }
}
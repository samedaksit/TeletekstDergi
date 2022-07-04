package com.example.teletekstdergi.util

import android.graphics.Canvas
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView

class CustomArticleDividerItemDecoration(color: Int, private val heightInPixels: Int) :
    RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init {
        paint.color = color
        paint.isAntiAlias = true
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val left = parent.paddingLeft + 8F
        val right = parent.width - parent.paddingRight - 8F
        val childCount = parent.childCount
        if (childCount > 1) {
            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)
                val params = child.layoutParams as RecyclerView.LayoutParams
                val top = child.bottom + params.bottomMargin
                val bottom = top + heightInPixels
                c.drawRect(left, top.toFloat(), right, bottom.toFloat(), paint)
            }
        }
    }
}
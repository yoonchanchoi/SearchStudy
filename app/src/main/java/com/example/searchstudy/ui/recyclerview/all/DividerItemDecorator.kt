package com.example.searchstudy.ui.recyclerview.all

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


//class DividerItemDecorator(private val mHeight: Float, a_color: Int) : ItemDecoration() {
//    private val mPaint = Paint()
//    init {
//        mPaint.color = a_color
//    }
//    override fun onDrawOver(a_canvas: Canvas, a_parent: RecyclerView, a_state: RecyclerView.State) {
//        super.onDrawOver(a_canvas, a_parent, a_state)
//        val left = a_parent.paddingLeft
//        val right = a_parent.width - a_parent.paddingRight
//        val childCount = a_parent.childCount
//        for (i in 0 until childCount-1) {
//            val child = a_parent.getChildAt(i)
//            val params = child.layoutParams as RecyclerView.LayoutParams
//            val top = child.bottom + params.bottomMargin
//            val bottom = top + mHeight
//            a_canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom, mPaint)
//        }
//    }
//}




class DividerItemDecorator(private val mDivider: Drawable) : RecyclerView.ItemDecoration() {


    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        val dividerLeft = parent.paddingLeft
        val dividerRight = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for(i in 0 until childCount -1){
            val child: View = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val dividerTop: Int = child.bottom + params.bottomMargin
            val dividerBottom = dividerTop + mDivider.intrinsicHeight
            mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            mDivider.draw(canvas)
        }

    }

//    fun onDrawOver(canvas: Canvas?, parent: RecyclerView, state: RecyclerView.State?) {
//        val dividerLeft = parent.paddingLeft
//        val dividerRight = parent.width - parent.paddingRight
//        val childCount = parent.childCount
//        for (i in 0..childCount - 2) {
//            val child: View = parent.getChildAt(i)
//            val params = child.getLayoutParams() as RecyclerView.LayoutParams
//            val dividerTop: Int = child.getBottom() + params.bottomMargin
//            val dividerBottom = dividerTop + mDivider.intrinsicHeight
//            mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
//            mDivider.draw(canvas)
//        }
//    }
}
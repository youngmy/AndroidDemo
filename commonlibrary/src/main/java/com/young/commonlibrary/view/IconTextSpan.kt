@file:Suppress("DEPRECATION")

package com.young.commonlibrary.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.FontMetricsInt
import android.graphics.Rect
import android.graphics.RectF
import android.text.TextPaint
import android.text.style.ReplacementSpan
import android.util.TypedValue
import com.young.commonlibrary.R

class IconTextSpan(
    private var mContext: Context,
    private var content: String = "默认", //Icon内文字
    private var bgColorResId: Int = R.color.color_main,//Icon背景颜色
    private var radius: Float = 3f,//Icon圆角半径
    private var rightMargin: Float = 0f,//右边距
    private var textSize: Float = 12f,//文字大小
    private var textColorResId: Int = R.color.color_ffffff,//文字颜色
) : ReplacementSpan() {
    private var mBgHeight: Float = 0f//Icon背景高度
    private var mBgWidth: Float = 0f //Icon背景宽度
    private var mRadius = 0f //Icon圆角半径
    private var mRightMargin = 0f //右边距
    private var mTextSize = 0f //文字大小

    init {
        //初始化默认数值
        initDefaultValue()
        //计算背景的宽度、高度
        calculateBgWidthHeight(content)
        //初始化画笔
        initPaint()
    }

    /**
     * 初始化画笔
     */
    private fun initPaint() {
        //初始化背景画笔
        //icon背景画笔
        val mBgPaint = Paint()
        mBgPaint.color = mContext.resources.getColor(bgColorResId)
        mBgPaint.style = Paint.Style.FILL
        mBgPaint.isAntiAlias = true

        //初始化文字画笔
        //icon文字画笔
        val mTextPaint: Paint = TextPaint()
        mTextPaint.color = mContext.resources.getColor(textColorResId)
        mTextPaint.textSize = mTextSize
        mTextPaint.isAntiAlias = true
        mTextPaint.textAlign = Paint.Align.CENTER
    }

    /**
     * 初始化默认数值
     */
    private fun initDefaultValue() {
        mTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSize, mContext.resources.displayMetrics)
        mRightMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightMargin, mContext.resources.displayMetrics)
        mRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radius, mContext.resources.displayMetrics)
    }

    /**
     * 计算icon背景宽度
     *
     * @param text icon内文字
     */
    private fun calculateBgWidthHeight(text: String) {
        if (text.isNotEmpty()) {
            //多字，宽度=文字宽度+padding
            val textRect = Rect()
            val paint = Paint()
            paint.textSize = mTextSize
            paint.getTextBounds(text, 0, text.length, textRect)
            val padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, mContext.resources.displayMetrics)
            mBgWidth = getRect(text).width() + padding * 2
            mBgHeight = getRect(
                if (text.isNotEmpty()) {
                    text.substring(0, 1)
                } else {
                    ""
                }
            ).height() + padding * 2
        } else {
            //单字，宽高一致为正方形 舍弃 需要留空隙
            mBgHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 17f, mContext.resources.displayMetrics)
            mBgWidth = mBgHeight
        }
    }

    private fun getRect(text: String): Rect {
        val textRect = Rect()
        val paint = Paint()
        paint.textSize = mTextSize
        paint.getTextBounds(text, 0, text.length, textRect)
        return textRect
    }


    /**
     * 设置右边距
     */
    fun setRightMarginDpValue(rightMarginDpValue: Int) {
        mRightMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightMarginDpValue.toFloat(), mContext.resources.displayMetrics)
    }

    /**
     * 设置宽度，宽度=背景宽度+右边距
     */
    override fun getSize(paint: Paint, text: CharSequence, start: Int, end: Int, fm: FontMetricsInt?): Int {
        return (mBgWidth + mRightMargin).toInt()
    }

    /**
     * draw
     *
     * @param text   完整文本
     * @param start  setSpan里设置的start
     * @param end    setSpan里设置的start
     * @param x       x
     * @param top    当前span所在行的上方y
     * @param y      y其实就是metric里baseline的位置
     * @param bottom 当前span所在行的下方y(包含了行间距)，会和下一行的top重合
     * @param paint  使用此span的画笔
     */
    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        //画背景
        val bgPaint = Paint()
        bgPaint.color = mContext.resources.getColor(bgColorResId)
        bgPaint.style = Paint.Style.FILL
        bgPaint.isAntiAlias = true
        val metrics = paint.fontMetrics
        val textHeight = metrics.descent - metrics.ascent
        //算出背景开始画的y坐标
        val bgStartY = y + (textHeight - mBgHeight) / 2 + metrics.ascent

        //画背景
        val bgRect = RectF(x, bgStartY, x + mBgWidth, bgStartY + mBgHeight)
        canvas.drawRoundRect(bgRect, mRadius, mRadius, bgPaint)

        //把字画在背景中间
        val textPaint = TextPaint()
        textPaint.color = mContext.resources.getColor(textColorResId)
        textPaint.textSize = mTextSize
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.CENTER //这个只针对x有效
        val fontMetrics = textPaint.fontMetrics
        val textRectHeight = fontMetrics.bottom - fontMetrics.top
        canvas.drawText(content, x + mBgWidth / 2, bgStartY + (mBgHeight - textRectHeight) / 2 - fontMetrics.top, textPaint)
    }


}
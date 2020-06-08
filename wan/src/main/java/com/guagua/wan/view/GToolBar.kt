package com.guagua.wan.view

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import com.guagua.wan.R

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/8 11:37
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class GToolBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr) {
    private var tvTitle: TextView? = null
    private var mImageButtonLeft: ImageButton? = null
    private var mImageButtonRight: ImageButton? = null
    private var mRightTextView: TextView? = null

    private var listener: OnGGToolbarClickListener? = null

    override fun setTitle(@StringRes resId: Int) {
        if (null == tvTitle) {
            tvTitle = addMiddleTitle(context, this)
        }
        tvTitle!!.setText(resId)
    }

    override fun setTitle(title: CharSequence?) {
        if (TextUtils.isEmpty(title)) {
            return
        }
        if (null == tvTitle) {
            tvTitle = addMiddleTitle(context, this)
        }
        tvTitle!!.text = title
    }

    /**
     * 设置标题粗体
     *
     * @param isBold
     */
    fun setTitleBold(isBold: Boolean) {
        if (null == tvTitle) {
            tvTitle = addMiddleTitle(context, this)
        }
        val paint = tvTitle!!.paint
        paint.isFakeBoldText = isBold
    }

    private fun addMiddleTitle(
        context: Context,
        toolbar: Toolbar
    ): TextView {
        val textView = TextView(context)
        textView.textSize = 17f
        textView.maxWidth = dip2px(context, 250.toFloat())
        textView.ellipsize = TextUtils.TruncateAt.END
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.gravity = Gravity.CENTER_HORIZONTAL
        toolbar.addView(textView, params)
        val paint = textView.paint
        paint.isFakeBoldText = true
        textView.setTextColor(resources.getColor(R.color.white))
        return textView
    }

    private fun addRightTextBtn(): TextView {
        val textView = TextView(context)
        textView.textSize = 16f
        textView.ellipsize = TextUtils.TruncateAt.END
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.gravity = Gravity.CENTER_VERTICAL or Gravity.RIGHT
        val pd: Int = dip2px(context, 10.toFloat())
        textView.setPadding(dip2px(context, 10.toFloat()), pd, dip2px(context, 20.toFloat()), pd)
        addView(textView, params)
        textView.setOnClickListener { v -> listener?.onRightTextClick(v) }
        return textView
    }

    private fun addLeftImgBtn(): ImageButton {
        val ib = ImageButton(context)
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
        val pd: Int = dip2px(context, 10.toFloat())
        ib.setPadding(0, pd, dip2px(context, 25.toFloat()), pd)
        addView(ib, params)
        ib.setOnClickListener { listener?.onLeftBtnClick(it) }
        return ib
    }

    private fun addRightImgBtn(): ImageButton {
        val ib = ImageButton(context)
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
        val pd: Int = dip2px(context, 10.toFloat())
        ib.setPadding(dip2px(context, 10.toFloat()), pd, dip2px(context, 25.toFloat()), pd)
        ib.layoutParams = params
        addView(ib)
        ib.setOnClickListener { listener?.onRightBtnClick(it) }
        return ib
    }

    fun setLeftBtnDrawable(@DrawableRes leftImgRes: Int): ImageButton? {
        if (null == mImageButtonLeft) {
            mImageButtonLeft = addLeftImgBtn()
        }
        mImageButtonLeft?.setBackgroundColor(resources.getColor(android.R.color.transparent))
        mImageButtonLeft?.setImageResource(leftImgRes)
        return mImageButtonLeft
    }

    fun setRightBtnDrawable(@DrawableRes rightImgRes: Int): ImageButton? {
        if (null == mImageButtonRight) {
            mImageButtonRight = addRightImgBtn()
        }
        mImageButtonRight?.setBackgroundColor(resources.getColor(android.R.color.transparent))
        mImageButtonRight?.setImageResource(rightImgRes)
        return mImageButtonRight
    }

    fun setRightTextBtnString(text: CharSequence?): TextView? {
        if (null == mRightTextView) {
            mRightTextView = addRightTextBtn()
        }
        mRightTextView?.text = text
        return mRightTextView
    }

    fun setLeftBtnVisibility(visibility: Int) {
        if (null != mImageButtonLeft) {
            mImageButtonLeft?.visibility = visibility
        }
    }

    fun setRightBtnVisibility(visibility: Int) {
        if (null != mImageButtonRight) {
            mImageButtonRight?.visibility = visibility
        }
    }

    interface OnGGToolbarClickListener {
        fun onLeftBtnClick(v: View?)
        fun onRightBtnClick(v: View?)
        fun onRightTextClick(v: View?)
    }

    fun setOnGGToolbarClickListener(listener: OnGGToolbarClickListener?) {
        this.listener = listener
    }

    /**
     * 根据手机分辨率从dp转成px
     *
     * @param context
     * @param dpValue
     * @return
     */
    private fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param context
     * @return
     */
    private fun sp2px(context: Context, spValue: Float): Int {
        val scale = context.resources.displayMetrics.scaledDensity
        return (spValue * scale + 0.5f).toInt()
    }
}
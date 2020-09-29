package com.lxc.jetpack

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.helper.widget.Layer
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView

/**
 * Copyright (C), 2020-2020, guagua
 * @author lxc
 * Date: 2020/9/28 10:30
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
val match_parent = ViewGroup.LayoutParams.MATCH_PARENT
val wrap_content = ViewGroup.LayoutParams.WRAP_CONTENT

inline fun ViewGroup.TextView(autoAdd: Boolean = true, init: TextView.() -> Unit) =
    TextView(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.ImageView(autoAdd: Boolean = true, init: ImageView.() -> Unit) =
    ImageView(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.Button(autoAdd: Boolean = true, init: Button.() -> Unit) =
    Button(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.View(autoAdd: Boolean = true, init: View.() -> Unit) =
    View(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.RelativeLayout(autoAdd: Boolean = true, init: RelativeLayout.() -> Unit) =
    RelativeLayout(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.LinearLayout(autoAdd: Boolean = true, init: LinearLayout.() -> Unit) =
    LinearLayout(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.NestedScrollView(autoAdd: Boolean = true, init: NestedScrollView.() -> Unit) =
    NestedScrollView(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.RecyclerView(autoAdd: Boolean = true, init: RecyclerView.() -> Unit) =
    RecyclerView(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.ConstraintLayout(autoAdd: Boolean = true, init: ConstraintLayout.() -> Unit) =
    ConstraintLayout(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.FrameLayout(autoAdd: Boolean = true, init: FrameLayout.() -> Unit) =
    FrameLayout(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.ViewFlipper(autoAdd: Boolean = true, init: ViewFlipper.() -> Unit) =
    ViewFlipper(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.EditText(autoAdd: Boolean = true, init: EditText.() -> Unit) =
    EditText(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ViewGroup.HorizontalScrollView(
    autoAdd: Boolean = true,
    init: HorizontalScrollView.() -> Unit
) =
    HorizontalScrollView(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ImageView.load(url: String, init: ImageView.() -> Unit) = ImageView(context).apply(init)
inline fun ImageView.selectors(url: String, init: ImageView.() -> Unit) = ImageView(context).apply(init)

//inline fun ViewGroup.ViewPager2(autoAdd: Boolean = true, init: ViewPager2.() -> Unit) =
//    ViewPager2(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ConstraintLayout.Guideline(autoAdd: Boolean = true, init: Guideline.() -> Unit) =
    Guideline(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ConstraintLayout.Flow(autoAdd: Boolean = true, init: Flow.() -> Unit) =
    Flow(context).apply(init).also { if (autoAdd) addView(it) }

inline fun ConstraintLayout.Layer(autoAdd: Boolean = true, init: Layer.() -> Unit) =
    Layer(context).apply(init).also { if (autoAdd) addView(it) }

inline fun Context.ConstraintLayout(init: ConstraintLayout.() -> Unit): ConstraintLayout =
    ConstraintLayout(this).apply(init)

inline fun Context.LinearLayout(init: LinearLayout.() -> Unit): LinearLayout =
    LinearLayout(this).apply(init)

inline var View.layout_width: Int
    get() {
        return 0
    }
    set(value) {
        val w = if (value > 0) value.dp else value
        var h = layoutParams?.height ?: 0
        layoutParams = ViewGroup.MarginLayoutParams(w, h)
    }

inline var View.layout_height: Int
    get() {
        return 0
    }
    set(value) {
        val w = layoutParams?.width ?: 0
        val h = if (value > 0) value.dp else value
        layoutParams = ViewGroup.MarginLayoutParams(w, h)
    }

inline var View.background_color: String
    get() {
        return ""
    }
    set(value) {
        setBackgroundColor(Color.parseColor(value))
    }

val Int.dp: Int
    get() {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()
    }

val Float.dp: Float
    get() {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            Resources.getSystem().displayMetrics
        )
    }
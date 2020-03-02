package com.sampletest.testproject.extensions

import android.content.Context
import android.content.res.Resources
import androidx.core.content.ContextCompat
import android.util.DisplayMetrics
import android.util.TypedValue

val displaymetrics = DisplayMetrics()

fun Int.resString(): String = Resources.getSystem().getString(this)

fun Int.resString(vararg formatArg: Any): String = Resources.getSystem().getString(this, *formatArg)

inline val Float.dp: Int
    get() {
        val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displaymetrics)

        return Math.round(px)
    }

inline val Int.dp: Int get() = this.toFloat().dp

inline val Float.sp: Int
    get() = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, displaymetrics))

inline val Int.sp: Int
    get() = toFloat().sp

fun Context.getDrawableCompat(id: Int) = ContextCompat.getDrawable(this, id)

fun Context.getColorCompat(id: Int) = ContextCompat.getColor(this, id)


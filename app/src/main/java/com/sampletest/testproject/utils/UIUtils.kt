package com.sampletest.testproject.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager


object UIUtils {
    @JvmStatic
    fun getScreenWidth(context: Context): Int {
        val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val metrics = DisplayMetrics()
        display.getMetrics(metrics)
        return metrics.widthPixels
    }

    @JvmStatic
    fun getScreenHeight(context: Context): Int {
        val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val metrics = DisplayMetrics()
        display.getMetrics(metrics)
        return metrics.heightPixels
    }

    @JvmStatic
    fun hideScreenStatusBar(activity: Activity) {
        activity.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    @JvmStatic
    fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
    }

    @JvmStatic
    fun isDeviceTablet(ctx: Context): Boolean {
        return ctx.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
    }

    @JvmStatic
    fun hideSoftKeyboard(activity: Activity?, forcely: Boolean) {
        val flag = if (forcely) 0 else InputMethodManager.HIDE_NOT_ALWAYS
        if (activity != null) {
            val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            if (activity.currentFocus != null)
                inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, flag)
        }
    }
}
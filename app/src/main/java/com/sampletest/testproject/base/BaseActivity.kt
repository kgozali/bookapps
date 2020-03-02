package com.sampletest.testproject.base

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.sampletest.testproject.R

abstract class BaseActivity : AppCompatActivity() {

    enum class SnackBarType { DEFAULT, SUCCESS, FAIL }

    var contentRes: Int? = null

    protected abstract fun toolbarOnClickListener(): ((View) -> Unit)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        contentRes?.let {
            setContentView(it)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary) //status bar or the time bar at the top
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
    
    fun showToast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun showSnackbar(anchor: View, msg: CharSequence, type: SnackBarType) {
        val sb = Snackbar.make(anchor, msg, Snackbar.LENGTH_LONG)
        val view = sb.view
        if (type != SnackBarType.DEFAULT) {
            view.setBackgroundColor(ContextCompat.getColor(
                baseContext,
                if (type == SnackBarType.SUCCESS) R.color.snackbar_success_color else R.color.snackbar_fail_color
            ))
        }
        sb.show()
    }

    fun showActionSnackbar(anchor: View, msg: CharSequence, type: SnackBarType, actionText: String, onClickListener: View.OnClickListener) {
        val sb = Snackbar.make(anchor, msg, Snackbar.LENGTH_LONG)
        val view = sb.view
        if (type != SnackBarType.DEFAULT) {
            view.setBackgroundColor(ContextCompat.getColor(
                baseContext,
                if (type == SnackBarType.SUCCESS) R.color.snackbar_success_color else R.color.snackbar_fail_color
            ))
        }

        if (type == SnackBarType.SUCCESS) {
            sb.setActionTextColor(ContextCompat.getColor(this, R.color.snackbar_success_action_button_color))
        }

        sb.setAction(actionText, onClickListener).show()
    }
}
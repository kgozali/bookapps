package com.sampletest.testproject.screens.bookdetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.sampletest.testproject.R
import com.sampletest.testproject.base.BaseActivity
import com.sampletest.testproject.extensions.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_default.*

class BookDetailActivity : BaseActivity() {

    private lateinit var mActionToolbar: Toolbar

    init {
        contentRes = R.layout.activity_default
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getSerializableExtra(BOOK_ID) as? Long ?: 0
        initToolbar()

        val fragment = supportFragmentManager.findFragmentById(R.id.flContent) as? BookDetailFragment ?:
            BookDetailFragment.newInstance().also {
                replaceFragmentInActivity(it, R.id.flContent)
            }

        BookDetailPresenter(fragment, id)
    }

    private fun initToolbar() {
        toolbarTitle.text = "Detail"

        mActionToolbar = findViewById<View>(R.id.atomic_toolbar) as Toolbar
        setSupportActionBar(mActionToolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        assert(true)
        mActionToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.sand))
        mActionToolbar.setNavigationOnClickListener { toolbarOnClickListener().invoke(it) }
        mActionToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
    }

    override fun toolbarOnClickListener(): (View) -> Unit = { onBackPressed() }

    companion object {
        const val BOOK_ID = "book_id"
    }
}
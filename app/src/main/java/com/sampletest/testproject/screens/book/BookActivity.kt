package com.sampletest.testproject.screens.book

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.sampletest.testproject.R
import com.sampletest.testproject.base.BaseActivity
import com.sampletest.testproject.extensions.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_default.*

class BookActivity : BaseActivity() {
    override fun toolbarOnClickListener(): (View) -> Unit = { onBackPressed() }
    private lateinit var mActionToolbar: Toolbar

    init {
        contentRes = R.layout.activity_default
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()

        val fragment = supportFragmentManager.findFragmentById(R.id.flContent) as BookFragment? ?:
        BookFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.flContent)
        }

        val genreId = intent.getSerializableExtra(GENRE_ID) as? Long ?: 0
        BookPresenter(genreId, fragment)
    }

    private fun initToolbar() {
        mActionToolbar = findViewById<View>(R.id.atomic_toolbar) as Toolbar
        setSupportActionBar(mActionToolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        assert(true)
        mActionToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.light_sand))
        mActionToolbar.setNavigationOnClickListener { toolbarOnClickListener().invoke(it) }
        mActionToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)

        toolbarTitle.text = resources.getString(R.string.title_book)
    }

    companion object {
        const val GENRE_ID = "genre_id"
    }
}
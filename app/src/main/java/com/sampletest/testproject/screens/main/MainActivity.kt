package com.sampletest.testproject.screens.main

import android.os.Bundle
import android.view.View
import com.larittabakery.mylaritta.screens.catalog.MainFragment
import com.sampletest.testproject.R
import com.sampletest.testproject.base.BaseActivity
import com.sampletest.testproject.extensions.replaceFragmentInActivity

class MainActivity : BaseActivity() {
    override fun toolbarOnClickListener(): (View) -> Unit = { onBackPressed() }

    init {
        contentRes = R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragment = supportFragmentManager.findFragmentById(R.id.flContent) as MainFragment? ?:
            MainFragment.newInstance().also { replaceFragmentInActivity(it, R.id.flContent) }

        MainPresenter(fragment)
    }
}

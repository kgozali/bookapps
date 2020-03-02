package com.sampletest.testproject.adapter

import android.view.View

interface GenericViewBinder<V : View?> {
    fun bindView(view: V, item: GenericViewController<V>?)
}
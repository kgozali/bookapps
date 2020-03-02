package com.sampletest.testproject.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup

interface GenericViewGenerator<V : View?> {
    fun generateView(ctx: Context, parent: ViewGroup): V
}
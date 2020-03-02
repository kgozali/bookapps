package com.sampletest.testproject.adapter

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.sampletest.testproject.utils.UIUtils

class AdapterExt {
    fun initializeAdapter(
        context: Context,
        rv: RecyclerView,
        orientation: Int = RecyclerView.VERTICAL,
        withDivider: Boolean = true
    ): FastItemAdapter<AbstractItem<*, *>> {
        val adapter: FastItemAdapter<AbstractItem<*, *>>
        if (rv.adapter == null) {
            adapter = FastItemAdapter()
            rv.itemAnimator = null
            if (rv.layoutManager == null) {
                val llm = LinearLayoutManager(context)
                if (withDivider) {
                    val dividerItemDecoration = DividerItemDecoration(rv.context, llm.orientation)
                    rv.addItemDecoration(dividerItemDecoration)
                }
                llm.orientation = orientation
                rv.layoutManager = llm
            }

            rv.adapter = adapter
        } else {
            adapter = rv.adapter as FastItemAdapter<AbstractItem<*, *>>
        }
        return adapter
    }

    fun initializeGridAdapter(
        context: Context,
        rv: RecyclerView
    ): FastItemAdapter<AbstractItem<*, *>> {
        val adapter: FastItemAdapter<AbstractItem<*, *>>
        if (rv.adapter == null) {
            adapter = FastItemAdapter()
            if (rv.layoutManager == null) {
                val llm = GridLayoutManager(
                    context,
                    if (UIUtils.isDeviceTablet(context)) TABLET_SPAN_COUNT else DEFAULT_SPAN_COUNT
                )
                rv.layoutManager = llm
            }

            rv.adapter = adapter
        } else adapter = rv.adapter as FastItemAdapter<AbstractItem<*, *>>
        return adapter
    }

    companion object {
        private const val DEFAULT_SPAN_COUNT = 4
        private const val TABLET_SPAN_COUNT = 6
    }
}
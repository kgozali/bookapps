package com.sampletest.testproject.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GenericViewHolder<V : View?>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val view: V = itemView as V
}
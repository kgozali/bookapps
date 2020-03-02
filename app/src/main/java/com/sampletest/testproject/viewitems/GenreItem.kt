package com.sampletest.testproject.viewitems

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.sampletest.testproject.R
import com.sampletest.testproject.adapter.GenericViewBinder
import com.sampletest.testproject.adapter.GenericViewController
import com.sampletest.testproject.adapter.GenericViewGenerator
import com.sampletest.testproject.api.model.GenreModel
import kotlinx.android.synthetic.main.item_genre_single.view.*

class GenreItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    private var state = State()

    init {
        View.inflate(this.context, R.layout.item_genre_single, this)
    }

    fun bind(bind: State.() -> Unit) {
        state.bind()
        render(state, this)
    }

    data class State (
        var genreItem: GenreModel? = null,
        var onClickListener: OnClickListener? = null
    )

    fun render(state: State, view: GenreItem) {

        state.genreItem?.let { genre ->
            tvCabang.apply {
                text = genre.title
            }
        }


        clContainer.setOnClickListener(state.onClickListener)
    }

    companion object {
        val classId = GenreItem::class.java.hashCode()
        fun item(init: State.() -> Unit): GenericViewController<GenreItem> {
            val state = State()
            state.init()

            val viewController = GenericViewController(
                type = classId,
                generator = object : GenericViewGenerator<GenreItem> {
                    override fun generateView(ctx: Context, parent: ViewGroup): GenreItem {
                        return GenreItem(ctx)
                    }
                }
            )

            return viewController.withBinder(object : GenericViewBinder<GenreItem> {
                override fun bindView(
                    view: GenreItem,
                    item: GenericViewController<GenreItem>?
                ) {
                    view.render(state, view)
                }
            })
        }
    }
}
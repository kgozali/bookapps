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
import kotlinx.android.synthetic.main.item_menu.view.*

class MenuItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    private var state = State()

    init {
        View.inflate(this.context, R.layout.item_menu, this)
    }

    fun bind(bind: State.() -> Unit) {
        state.bind()
        render(state)
    }

    data class State (
        var title: String? = null,
        var onClickListener: OnClickListener? = null
    )

    fun render(state: State) {
        state.title?.let { title ->
            tvTitle.apply {
                text = title
            }
        }

        clContainer.setOnClickListener(state.onClickListener)
    }

    companion object {
        val classId = MenuItem::class.java.hashCode()
        fun item(init: State.() -> Unit): GenericViewController<MenuItem> {
            val state = State()
            state.init()

            val viewController = GenericViewController(
                type = classId,
                generator = object : GenericViewGenerator<MenuItem> {
                    override fun generateView(ctx: Context, parent: ViewGroup): MenuItem {
                        return MenuItem(ctx)
                    }
                }
            )

            return viewController.withBinder(object : GenericViewBinder<MenuItem> {
                override fun bindView(
                    view: MenuItem,
                    item: GenericViewController<MenuItem>?
                ) {
                    view.render(state)
                }
            })
        }
    }
}
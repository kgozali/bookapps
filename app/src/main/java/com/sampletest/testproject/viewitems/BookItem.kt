package com.sampletest.testproject.viewitems

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sampletest.testproject.R
import com.sampletest.testproject.adapter.GenericViewBinder
import com.sampletest.testproject.adapter.GenericViewController
import com.sampletest.testproject.adapter.GenericViewGenerator
import com.sampletest.testproject.api.model.BookModel
import com.sampletest.testproject.utils.TextUtils
import com.sampletest.testproject.utils.UIUtils
import kotlinx.android.synthetic.main.item_book.view.*

class BookItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var state = State()

    init {
        View.inflate(this.context, R.layout.item_book, this)
    }

    fun bind(bind: State.() -> Unit) {
        state.bind()
        render(state)
    }

    data class State (
        var bookData: BookModel? = null,
        var onClickListener: OnClickListener? = null
    )

    fun render(state: State) {
        state.bookData?.let { data ->
            val size = UIUtils.getScreenWidth(context) - UIUtils.convertDpToPixel(32f, context).toInt()
            ivMain.apply {
                data.coverUrl?.let {
                    Glide.with(this)
                            .load(TextUtils().getUrl(it))
                            .apply(RequestOptions()
                                    .override(size, size))
                            .into(ivMain)
                }
            }

            tvTitle.apply {
                data.title?.let {
                    text = it
                }
            }

            tvCondition.apply {
                text = if (data.isNew) "Baru" else "Bekas"
            }

            tvRating.apply {
                text = context.getString(R.string.rating_text, data.rating.toString())
            }

            llContainer.setOnClickListener(state.onClickListener)
        }
    }

    companion object {
        val classId = BookItem::class.java.hashCode()
        fun item(init: State.() -> Unit): GenericViewController<BookItem> {
            val state = State()
            state.init()

            val viewController = GenericViewController(
                type = classId,
                generator = object : GenericViewGenerator<BookItem> {
                    override fun generateView(ctx: Context, parent: ViewGroup): BookItem {
                        return BookItem(ctx).apply {
                            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                        }
                    }
                }
            )

            return viewController.withBinder(object : GenericViewBinder<BookItem> {
                override fun bindView(
                    view: BookItem,
                    item: GenericViewController<BookItem>?
                ) {
                    view.render(state)
                }
            })
        }
    }
}
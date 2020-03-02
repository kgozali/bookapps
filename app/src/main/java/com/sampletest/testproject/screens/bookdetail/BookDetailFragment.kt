package com.sampletest.testproject.screens.bookdetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sampletest.testproject.R
import com.sampletest.testproject.api.response.BookDetailResponse
import com.sampletest.testproject.base.BaseFragment
import com.sampletest.testproject.utils.TextUtils
import com.sampletest.testproject.utils.UIUtils
import kotlinx.android.synthetic.main.fragment_book_detail.*

class BookDetailFragment : BaseFragment(), BookDetailContract.View {

    override lateinit var presenter: BookDetailContract.Presenter

    override var isFragmentReady = false
        get() = isAdded

    override var isFragmentDestroyed: Boolean = false
        get() = isDestroyed()

    init {
        contentRes = R.layout.fragment_book_detail
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
    }

    override fun renderContent(data: BookDetailResponse?) {
        if (presenter.isFetching) {
            loadingIndicator.visibility = View.VISIBLE
        } else {
            loadingIndicator.visibility = View.GONE
            data?.let { response ->
                context?.let { ctx ->
                    val width = UIUtils.getScreenWidth(ctx)
                    val height = width / 2

                    Glide.with(ctx)
                        .load(TextUtils().getUrl(response.result?.coverUrl.orEmpty()))
                        .apply(RequestOptions().override(width, height))
                        .into(ivMain)

                    tvTitle.text = response.result?.title

                    tvAuthorDetail.apply {
                        text = getString(R.string.author_detail)
                        setOnClickListener { onAuthorClick(response.result?.writerId ?: 0) }
                    }

                    tvDescription.text = TextUtils().parseHtml(response.result?.sypnopsis.toString())
                }
            }
        }
    }

    override fun onAuthorClick(id: Long) {
        val intent = Intent(context, WriterDetailActivity::class.java)
        intent.putExtra(WriterDetailActivity.WRITER_ID, id)
        startActivity(intent)
    }

    companion object {
        fun newInstance(): BookDetailFragment {
            return BookDetailFragment()
        }
    }
}
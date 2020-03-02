package com.sampletest.testproject.screens.writer

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sampletest.testproject.R
import com.sampletest.testproject.api.response.WriterResponse
import com.sampletest.testproject.base.BaseFragment
import com.sampletest.testproject.screens.bookdetail.WriterDetailContract
import com.sampletest.testproject.utils.TextUtils
import com.sampletest.testproject.utils.UIUtils
import kotlinx.android.synthetic.main.fragment_writer.*
import org.w3c.dom.Text

class WriterDetailFragment : BaseFragment(), WriterDetailContract.View {

    override lateinit var presenter: WriterDetailContract.Presenter

    override var isFragmentReady = false
        get() = isAdded

    override var isFragmentDestroyed: Boolean = false
        get() = isDestroyed()

    init {
        contentRes = R.layout.fragment_writer
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
    }

    override fun renderContent(data: WriterResponse?) {
        if (presenter.isFetching) {
            loadingIndicator.visibility = View.VISIBLE
        } else {
            loadingIndicator.visibility = View.GONE
            data?.let { response ->
                context?.let { ctx ->
                    val width = UIUtils.getScreenWidth(ctx)
                    val height = width / 2

                    Glide.with(ctx)
                        .load(TextUtils().getUrl(response.result?.photoUrl.orEmpty()))
                        .apply(RequestOptions().override(width, height))
                        .into(ivMain)

                    tvName.text = response.result?.name

                    tvUsername.text = response.result?.username

                    tvFollower.text = resources.getString(
                        R.string.follower_text,
                        response.result?.follower.toString()
                    )
                }
            }
        }
    }

    companion object {
        fun newInstance(): WriterDetailFragment {
            return WriterDetailFragment()
        }
    }
}
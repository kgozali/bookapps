package com.sampletest.testproject.screens.bookdetail

import com.sampletest.testproject.api.ApiBuilder
import com.sampletest.testproject.api.response.BookDetailResponse
import com.sampletest.testproject.base.BasePresenter
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BookDetailPresenter(
    private val view: BookDetailContract.View,
    private val bookId: Long
) : BasePresenter(), BookDetailContract.Presenter {

    override var responseData: BookDetailResponse? = null

    override var isFetching: Boolean = false

    init {
        view.presenter = this
    }

    private fun callRender() {
        if (!view.isFragmentDestroyed) view.renderContent(responseData)
    }

    override fun start() {
        isFetching = true
        callRender()
        fetchData()
    }

    override fun fetchData(): Job = launch {
        ApiBuilder.retrieveBookDetail(
            bookId,
            onSuccess = { response ->
                responseData = response.body()
                isFetching = false
                callRender()
            },
            onFailure = {
                isFetching = false
                callRender()
            }
        )
    }
}
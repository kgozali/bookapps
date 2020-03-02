package com.sampletest.testproject.screens.book

import com.sampletest.testproject.api.ApiBuilder
import com.sampletest.testproject.api.response.BookResponse
import com.sampletest.testproject.base.BasePresenter

class BookPresenter(
    private val genreId: Long,
    private val view: BookContract.View
) : BasePresenter(), BookContract.Presenter {
    override var responseData: BookResponse? = null

    override var isFetching: Boolean = false

    init {
        view.presenter = this
    }

    private fun callRender() {
        if (!view.isFragmentDestroyed) view.render()
    }

    override fun start() {
        isFetching = true
        callRender()
        fetchData()
    }

    override fun fetchData() {
        ApiBuilder.retrieveBooksByGenre(
            id = genreId,
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
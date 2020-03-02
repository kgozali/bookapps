package com.sampletest.testproject.screens.bookdetail

import com.sampletest.testproject.api.ApiBuilder
import com.sampletest.testproject.api.response.WriterResponse
import com.sampletest.testproject.base.BasePresenter
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WriterDetailPresenter(
    private val view: WriterDetailContract.View,
    private val userId: Long
) : BasePresenter(), WriterDetailContract.Presenter {

    override var responseData: WriterResponse? = null

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
        ApiBuilder.retrieveWriter(
            userId,
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
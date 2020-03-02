package com.sampletest.testproject.screens.main

import com.sampletest.testproject.api.ApiBuilder
import com.sampletest.testproject.api.response.GenreResponse
import com.sampletest.testproject.base.BasePresenter
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainPresenter(
    private val view: MainContract.View
) : BasePresenter(), MainContract.Presenter {

    override var responseData: GenreResponse? = null

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

    override fun fetchData(): Job = launch {
        ApiBuilder.retrieveGenres(
            onSuccess = { response ->
                responseData = response.body()
                isFetching = false
                view.render()
            },
            onFailure = {
                isFetching = false
                view.render()
            }
        )
    }
}
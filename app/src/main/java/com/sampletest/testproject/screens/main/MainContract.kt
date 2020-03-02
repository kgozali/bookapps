package com.sampletest.testproject.screens.main

import com.sampletest.testproject.api.response.GenreResponse
import com.sampletest.testproject.base.BaseContractPresenter
import com.sampletest.testproject.base.BaseView
import com.sampletest.testproject.base.BaseViewContract
import kotlinx.coroutines.Job

interface MainContract {
    interface View : BaseView<Presenter>, BaseViewContract {
        fun render()

        fun onGenreClick(id: Long)
    }

    interface Presenter : BaseContractPresenter {
        var responseData: GenreResponse?
        var isFetching: Boolean

        fun fetchData(): Job
    }
}
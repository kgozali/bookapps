package com.sampletest.testproject.screens.book

import com.sampletest.testproject.api.response.BookResponse
import com.sampletest.testproject.base.BaseContractPresenter
import com.sampletest.testproject.base.BaseView
import com.sampletest.testproject.base.BaseViewContract

interface BookContract {
    interface View : BaseView<Presenter>, BaseViewContract {
        fun setupView()

        fun render()

        fun onItemClick(id: Long)
    }

    interface Presenter : BaseContractPresenter {
        var responseData: BookResponse?
        var isFetching: Boolean

        fun fetchData()
    }
}
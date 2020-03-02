package com.sampletest.testproject.screens.bookdetail

import com.sampletest.testproject.api.response.BookDetailResponse
import com.sampletest.testproject.base.BaseContractPresenter
import com.sampletest.testproject.base.BaseView
import com.sampletest.testproject.base.BaseViewContract
import kotlinx.coroutines.Job

interface BookDetailContract {

    interface View : BaseView<Presenter>, BaseViewContract {
        fun renderContent(data: BookDetailResponse?)
        fun onAuthorClick(id: Long)
    }

    interface Presenter : BaseContractPresenter {
        var responseData: BookDetailResponse?
        var isFetching: Boolean

        fun fetchData(): Job
    }
}
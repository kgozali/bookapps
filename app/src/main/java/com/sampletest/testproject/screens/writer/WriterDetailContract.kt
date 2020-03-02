package com.sampletest.testproject.screens.bookdetail

import com.sampletest.testproject.api.response.WriterResponse
import com.sampletest.testproject.base.BaseContractPresenter
import com.sampletest.testproject.base.BaseView
import com.sampletest.testproject.base.BaseViewContract
import kotlinx.coroutines.Job

interface WriterDetailContract {

    interface View : BaseView<Presenter>, BaseViewContract {
        fun renderContent(data: WriterResponse?)
    }

    interface Presenter : BaseContractPresenter {
        var responseData: WriterResponse?
        var isFetching: Boolean

        fun fetchData(): Job
    }
}
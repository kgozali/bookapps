package com.sampletest.testproject.api.response

import com.google.gson.annotations.SerializedName
import com.sampletest.testproject.api.model.BookDetailModel

class BookDetailResponse {
    @SerializedName("success")
    var success: Boolean = false
    @SerializedName("result")
    var result: BookDetailModel? = null
}
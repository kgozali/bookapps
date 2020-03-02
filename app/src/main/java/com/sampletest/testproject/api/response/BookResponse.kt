package com.sampletest.testproject.api.response

import com.google.gson.annotations.SerializedName
import com.sampletest.testproject.api.model.BookModel

class BookResponse {
    @SerializedName("result")
    var resource: List<BookModel>? = null
}
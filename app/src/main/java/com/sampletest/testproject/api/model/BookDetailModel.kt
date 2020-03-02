package com.sampletest.testproject.api.model

import com.google.gson.annotations.SerializedName

class BookDetailModel {
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("cover_url")
    var coverUrl: String? = null
    @SerializedName("synopsis")
    var sypnopsis: String? = null
    @SerializedName("writer_id")
    var writerId: Long? = null
}
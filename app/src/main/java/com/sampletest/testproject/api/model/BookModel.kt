package com.sampletest.testproject.api.model

import com.google.gson.annotations.SerializedName

class BookModel {
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("cover_url")
    var coverUrl: String? = null
    @SerializedName("isNew")
    var isNew: Boolean = false
    @SerializedName("rate_sum")
    var rating: Float = 0f
    @SerializedName("view_count")
    var viewCount: Long = 0
    @SerializedName("Writer_by_writer_id")
    var writer: WriterSimpleDetail? = null
}
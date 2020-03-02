package com.sampletest.testproject.api.model

import com.google.gson.annotations.SerializedName

class GenreModel {
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("icon_url")
    var iconUrl: String? = null
    @SerializedName("count")
    var count: Long? = null
}
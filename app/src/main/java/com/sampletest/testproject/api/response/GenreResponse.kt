package com.sampletest.testproject.api.response

import com.google.gson.annotations.SerializedName
import com.sampletest.testproject.api.model.GenreModel

class GenreResponse {
    @SerializedName("resource")
    var resource: List<GenreModel>? = null
}
package com.sampletest.testproject.api.model

import com.google.gson.annotations.SerializedName

class WriterModel {
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("username")
    var username: String? = null
    @SerializedName("photo_url")
    var photoUrl: String? = null
    @SerializedName("count_follower")
    var follower: Long = 0
}
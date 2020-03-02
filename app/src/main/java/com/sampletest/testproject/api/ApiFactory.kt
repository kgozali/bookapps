package com.sampletest.testproject.api

/**
 * Created by gozali on 31/01/18.
 */
object ApiFactory {
    @JvmStatic
    inline fun <reified T> service() : T {
        return ApiClient.retrofitBuilder().create(T::class.java)
    }
}
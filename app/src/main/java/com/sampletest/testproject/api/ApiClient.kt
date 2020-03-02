package com.sampletest.testproject.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient

/**
 * Created by gozali on 31/01/18.
 */
object ApiClient {
    private const val API_BASE_URL = "https://cabaca.id:8443/api/v2/"

    private val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .retryOnConnectionFailure(true)
            .build()

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun retrofitBuilder(): Retrofit {
        return retrofit
    }
}
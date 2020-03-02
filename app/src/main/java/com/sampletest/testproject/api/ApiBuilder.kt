package com.sampletest.testproject.api

import com.sampletest.testproject.api.response.BookDetailResponse
import com.sampletest.testproject.api.response.BookResponse
import com.sampletest.testproject.api.response.GenreResponse
import com.sampletest.testproject.api.response.WriterResponse
import com.sampletest.testproject.api.services.ApiService
import com.sampletest.testproject.singleton.Constants.API_TOKEN
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApiBuilder {
    fun retrieveGenres(
        onSuccess: (Response<GenreResponse>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        ApiFactory.service<ApiService>()
            .retrieveTableGenre(API_TOKEN)
            .enqueue(object : Callback<GenreResponse> {
                override fun onResponse(
                    call: Call<GenreResponse>,
                    response: Response<GenreResponse>
                ) {
                    onSuccess.invoke(response)
                }

                override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                    onFailure.invoke(t)
                }
            })

    }

    fun retrieveBooksByGenre(
        id: Long,
        onSuccess: (Response<BookResponse>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        ApiFactory.service<ApiService>()
            .retrieveBookCategory(API_TOKEN, id)
            .enqueue(object : Callback<BookResponse> {
                override fun onResponse(
                    call: Call<BookResponse>,
                    response: Response<BookResponse>
                ) {
                    onSuccess.invoke(response)
                }

                override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                    onFailure.invoke(t)
                }
            })

    }

    fun retrieveBookDetail(
        id: Long,
        onSuccess: (Response<BookDetailResponse>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        ApiFactory.service<ApiService>()
            .retrieveBookDetail(API_TOKEN, id)
            .enqueue(object : Callback<BookDetailResponse> {
                override fun onFailure(call: Call<BookDetailResponse>, t: Throwable) {
                    onFailure.invoke(t)
                }

                override fun onResponse(
                    call: Call<BookDetailResponse>,
                    response: Response<BookDetailResponse>
                ) {
                    onSuccess.invoke(response)
                }

            })
    }

    fun retrieveWriter(
        userId: Long,
        onSuccess: (Response<WriterResponse>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        ApiFactory.service<ApiService>()
            .retrieveWriter(API_TOKEN, userId)
            .enqueue(object : Callback<WriterResponse> {
                override fun onFailure(call: Call<WriterResponse>, t: Throwable) {
                    onFailure.invoke(t)
                }

                override fun onResponse(
                    call: Call<WriterResponse>,
                    response: Response<WriterResponse>
                ) {
                    onSuccess.invoke(response)
                }

            })
    }
}
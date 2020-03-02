package com.sampletest.testproject.api.services

import com.sampletest.testproject.api.response.BookDetailResponse
import com.sampletest.testproject.api.response.BookResponse
import com.sampletest.testproject.api.response.GenreResponse
import com.sampletest.testproject.api.response.WriterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("cabaca/_table/genre")
    fun retrieveTableGenre(
        @Header("x-dreamfactory-api-key") token: String
    ) : Call<GenreResponse>

    @GET("book/category")
    fun retrieveBookCategory(
        @Header("x-dreamfactory-api-key") token: String,
        @Query("id") genreId: Long
    ) : Call<BookResponse>

    @GET("book/uptodate?limit={limit}")
    fun retrieveBookList(
        @Header("x-dreamfactory-api-key") token: String,
        @Path("limit") limit: Long
    ) : Call<BookResponse>

    @GET("book/detail/{book_id}")
    fun retrieveBookDetail(
        @Header("x-dreamfactory-api-key") token: String,
        @Path("book_id") bookId: Long
    ) : Call<BookDetailResponse>

    @GET("writer/detail/{user_id}")
    fun retrieveWriter(
        @Header("x-dreamfactory-api-key") token: String,
        @Path("user_id") userId: Long
    ) : Call<WriterResponse>
}
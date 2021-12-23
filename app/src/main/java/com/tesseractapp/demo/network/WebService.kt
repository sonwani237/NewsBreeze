package com.tesseractapp.demo.network

import com.google.gson.JsonElement
import com.tesseractapp.demo.models.HeadlineModel
import com.tesseractapp.demo.utils.Urls.HEADLINE
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET(HEADLINE)
    suspend fun headline(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<JsonElement>
}
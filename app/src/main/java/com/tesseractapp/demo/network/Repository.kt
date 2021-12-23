package com.tesseractapp.demo.network

import com.google.gson.JsonElement
import com.tesseractapp.demo.models.HeadlineModel
import com.tesseractapp.demo.utils.ApplicationConstant
import io.reactivex.Observable
import retrofit2.Response

class Repository(private val apiCallInterface: WebService) {

    suspend fun executeHeadline(country:String): Response<JsonElement> {
        return apiCallInterface.headline(country, ApplicationConstant.API_KEY)
    }



}

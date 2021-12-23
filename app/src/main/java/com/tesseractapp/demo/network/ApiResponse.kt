package com.tesseractapp.demo.network

import android.util.Log
import com.google.gson.JsonElement

class ApiResponse (val status: Status, val data: JsonElement?, val error: String?) {
    companion object {

        fun loading(): ApiResponse {
            return ApiResponse(Status.LOADING, null, null)
        }

        fun success(data: JsonElement): ApiResponse {
            return ApiResponse(Status.SUCCESS, data, null)
        }

        fun error(error: String): ApiResponse {
            Log.e("ApiResponse", "error: " + error)
            return ApiResponse(Status.ERROR, null, error)
        }
    }

}


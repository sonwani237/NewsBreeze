package com.tesseractapp.demo.models

data class HeadlineModel(val status: String, val articles: ArrayList<Articles>) {

    data class Articles(
        val title: String,
        val description: String,
        val urlToImage: String,
        val publishedAt: String,
        val content: String,
        val author: String,
        var saved: Boolean = false
    )

}

package com.tesseractapp.demo.listeners

import com.tesseractapp.demo.models.HeadlineModel

interface NewsListener {
    fun onSave(articles: HeadlineModel.Articles)
}
package com.tesseractapp.demo.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.tesseractapp.demo.models.HeadlineModel
import com.tesseractapp.demo.network.Repository
import com.tesseractapp.demo.utils.SingleLiveEvent

class DetailViewModel(private val repository: Repository) : ViewModel()  {

    private val backClick: SingleLiveEvent<Void> = SingleLiveEvent()
    var image: ObservableField<String> = ObservableField()
    var date: ObservableField<String> = ObservableField()
    var title: ObservableField<String> = ObservableField()
    var author: ObservableField<String> = ObservableField()
    var detail: ObservableField<String> = ObservableField()

    fun onBackClick(): SingleLiveEvent<Void> {
        return backClick
    }

    fun onBack() {
        backClick.call()
    }

    fun setData(mData: HeadlineModel.Articles?) {
        image.set(mData?.urlToImage)
        date.set(mData?.publishedAt)
        title.set(mData?.title)
        author.set(mData?.author)
        detail.set(mData?.description)
    }



}
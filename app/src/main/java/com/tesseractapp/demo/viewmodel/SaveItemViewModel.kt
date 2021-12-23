package com.tesseractapp.demo.viewmodel

import androidx.databinding.BaseObservable
import com.tesseractapp.demo.R
import com.tesseractapp.demo.listeners.NewsListener
import com.tesseractapp.demo.models.HeadlineModel

class SaveItemViewModel(model: HeadlineModel.Articles?, val mListener: NewsListener) : BaseObservable(){

    private var mModel: HeadlineModel.Articles? =null

    init {
        this.mModel = model
    }

    fun setData(mList: HeadlineModel.Articles) {
        mModel = mList
        notifyChange()
    }

    fun getTitle() : String{
        return mModel?.title?:"N/A"
    }

    fun getNews() : String{
        return mModel?.description?:"N/A"
    }

    fun getDate() : String{
        return mModel?.publishedAt?:"N/A" +" "+mModel?.author?:"N/A"
    }

    fun getImage() : String{
        return mModel?.urlToImage?:""
    }

    fun onRead() {
        mListener.onSave(mModel!!)
    }


}
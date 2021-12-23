package com.tesseractapp.demo.viewmodel

import androidx.databinding.BaseObservable
import com.tesseractapp.demo.R
import com.tesseractapp.demo.listeners.NewsListener
import com.tesseractapp.demo.models.HeadlineModel

class NewsItemViewModel(model: HeadlineModel.Articles?, val mListener: NewsListener) : BaseObservable(){

    private var mModel: HeadlineModel.Articles? =null

    init {
        this.mModel = model
    }

    fun setData(mList: HeadlineModel.Articles) {
        mModel = mList
        notifyChange()
    }

    fun getTitle() : String{
        return mModel?.title?:""
    }

    fun getNews() : String{
        return mModel?.description?:"N/A"
    }

    fun getDate() : String{
        return mModel?.publishedAt?:"N/A"
    }

    fun getImage() : String{
        return mModel?.urlToImage?:""
    }

    fun getLoadDrawable() : Int{
        return if (mModel!!.saved) {
            R.drawable.save
        } else {
            R.drawable.no_save
        }
    }

    fun onSave() {
        mModel!!.saved = !mModel!!.saved
        notifyChange()
    }

    fun onRead() {
        mListener.onSave(mModel!!)
    }


}
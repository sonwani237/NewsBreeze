package com.tesseractapp.demo.viewmodel

import androidx.lifecycle.ViewModel
import com.tesseractapp.demo.network.Repository
import com.tesseractapp.demo.utils.SingleLiveEvent

class SaveViewModel(private val repository: Repository) : ViewModel()  {

    private val backClick: SingleLiveEvent<Void> = SingleLiveEvent()

    fun onBackClick(): SingleLiveEvent<Void> {
        return backClick
    }

    fun onBack() {
        backClick.call()
    }
}
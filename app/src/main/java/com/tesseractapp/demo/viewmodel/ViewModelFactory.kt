package com.tesseractapp.demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tesseractapp.demo.network.Repository
import javax.inject.Inject

class ViewModelFactory
@Inject constructor(val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(SaveViewModel::class.java)) {
            return SaveViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class name in ViewModelFactory.class")
    }
}

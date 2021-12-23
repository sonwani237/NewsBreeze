package com.tesseractapp.demo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tesseractapp.demo.network.ApiResponse
import com.tesseractapp.demo.network.Repository
import com.tesseractapp.demo.utils.SingleLiveEvent
import com.tesseractapp.demo.utils.UtilMethods
import kotlinx.coroutines.*

class DashboardViewModel(private val repository: Repository) : ViewModel() {

    val apiResponse = MutableLiveData<ApiResponse>()
    private var job: Job? = null

    private val saveClick: SingleLiveEvent<Void> = SingleLiveEvent()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun getHeadline(country: String) {
        job?.cancel()
        ApiResponse.loading()
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            try {
                val asyncPost = async {
                    repository.executeHeadline(country)
                }

                val response = asyncPost.await()

                if (response.isSuccessful) {
                    viewModelScope.launch(Dispatchers.Main) {
                        apiResponse.value = response.body()?.let { ApiResponse.success(it) }
                    }
                } else {
                    viewModelScope.launch(Dispatchers.Main) {
                        apiResponse.value = ApiResponse.error(response.errorBody().toString())
                    }
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    ApiResponse.error(e.message.toString())
                    UtilMethods.printLog("Success", ">>> $e")
                }
            }
        }
    }

    fun onSaveClick(): SingleLiveEvent<Void> {
        return saveClick
    }

    fun onSave() {
        saveClick.call()
    }

}
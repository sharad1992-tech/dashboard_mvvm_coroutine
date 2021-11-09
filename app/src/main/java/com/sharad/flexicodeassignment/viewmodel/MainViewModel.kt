package com.sharad.flexicodeassignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sharad.flexicodeassignment.model.Data
import com.sharad.flexicodeassignment.repo.MainRepository



import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository):ViewModel() {
    val dataList = MutableLiveData<Data>()
    var job: Job? = null

    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun getCategoryItemsList(){
        job = CoroutineScope(Dispatchers.IO ).launch {

            val response = mainRepository.getData()
            withContext(Dispatchers.Main){
                if (response!!.isSuccessful){
                    dataList.postValue(response?.body())
                }else{
                    onError("Error : ${response?.message()} ")
                }
            }
        }
    }



    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
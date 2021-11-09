package com.wegolook.itworks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sharad.flexicodeassignment.MainViewModel
import com.sharad.flexicodeassignment.repo.MainRepository


class ViewModelFactory(private val mainRepository: MainRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(this.mainRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}
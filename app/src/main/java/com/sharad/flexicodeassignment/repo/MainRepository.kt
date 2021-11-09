package com.sharad.flexicodeassignment.repo

import com.sharad.flexicodeassignment.RetrofitService


class MainRepository constructor (private val retrofitService: RetrofitService) {


    suspend fun getData() = retrofitService?.getData("q")
}
package com.sharad.flexicodeassignment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Data {

    @SerializedName("total_count")
    val totalCount: Int? = null

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null

    @SerializedName("items")
    val items: List<Item>? = null

    var expand : Boolean = false
}
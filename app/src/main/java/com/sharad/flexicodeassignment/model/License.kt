package com.sharad.flexicodeassignment.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class License {

    @SerializedName("key")
    val key: String? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("spdx_id")
    val spdxId: String? = null

    @SerializedName("url")
    val url: String? = null

    @SerializedName("node_id")
    val nodeId: String? = null
}
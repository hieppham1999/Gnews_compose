package com.hieppt.enrich.gnew.data.model

import com.google.gson.annotations.SerializedName

data class User constructor(
    @field:SerializedName ("username") val userName: String,
    @field:SerializedName ("avatar") val avatar: String? = null,
)

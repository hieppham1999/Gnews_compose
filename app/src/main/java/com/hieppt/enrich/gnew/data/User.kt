package com.hieppt.enrich.gnew.data

import com.google.gson.annotations.SerializedName

data class User(
    @field:SerializedName ("username") val userName: String,
    @field:SerializedName ("password") val password: String,
    @field:SerializedName ("email") val email: String,
    @field:SerializedName ("avatar") val avatar: String,
)

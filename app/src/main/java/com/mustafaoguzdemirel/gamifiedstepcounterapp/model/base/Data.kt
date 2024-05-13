package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("innerData")
    @Expose
    val innerData: String? = null

    @SerializedName("message")
    @Expose
    val message: String? = null
}
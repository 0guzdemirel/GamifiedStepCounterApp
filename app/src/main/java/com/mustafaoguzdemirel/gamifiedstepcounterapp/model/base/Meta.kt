package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Meta {
    @SerializedName("code")
    @Expose
    val code: Int? = null

    @SerializedName("message")
    @Expose
    val message: String? = null
}

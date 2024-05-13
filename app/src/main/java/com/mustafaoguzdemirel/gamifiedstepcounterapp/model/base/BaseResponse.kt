package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseResponse {
    @SerializedName("meta")
    @Expose
    val meta: Meta? = null

    @SerializedName("data")
    @Expose
    val data: Data? = null
}

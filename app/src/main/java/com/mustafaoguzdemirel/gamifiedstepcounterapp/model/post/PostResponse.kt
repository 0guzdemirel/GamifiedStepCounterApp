package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.post

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.base.Meta

class PostResponse {
    @SerializedName("meta")
    @Expose
    val meta: Meta? = null

    @SerializedName("data")
    @Expose
    val postData: PostData? = null
}

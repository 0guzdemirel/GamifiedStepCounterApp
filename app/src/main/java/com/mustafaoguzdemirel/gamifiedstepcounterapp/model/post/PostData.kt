package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.post

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PostData {
    @SerializedName("innerData")
    @Expose
    val postList: MutableList<PostModel?>? = null

    @SerializedName("message")
    @Expose
    val message: String? = null
}

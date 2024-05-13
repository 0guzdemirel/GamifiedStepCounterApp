package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.comment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CommentData {
    @SerializedName("innerData")
    @Expose
    val commentList: MutableList<CommentModel?>? = null

    @SerializedName("message")
    @Expose
    val message: String? = null
}

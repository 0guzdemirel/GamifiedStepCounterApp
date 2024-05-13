package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.comment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.base.Meta

class CommentResponse {
    @SerializedName("meta")
    @Expose
    val meta: Meta? = null

    @SerializedName("data")
    @Expose
    val commentData: CommentData? = null
}

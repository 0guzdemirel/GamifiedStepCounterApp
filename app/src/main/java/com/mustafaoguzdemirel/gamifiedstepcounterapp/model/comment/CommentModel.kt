package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.comment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.UserModel

class CommentModel {
    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("description")
    @Expose
    val description: String? = null

    @SerializedName("createdDate")
    @Expose
    val createdDate: String? = null

    @SerializedName("parentPostId")
    @Expose
    val parentPostId: String? = null

    @SerializedName("userModel")
    @Expose
    val userModel: UserModel? = null
}
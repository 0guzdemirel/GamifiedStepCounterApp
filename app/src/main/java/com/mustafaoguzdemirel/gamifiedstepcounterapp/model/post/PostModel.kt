package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.post

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.UserModel

class PostModel {
    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("description")
    @Expose
    val description: String? = null

    @SerializedName("createdDate")
    @Expose
    val createdDate: String? = null

    @SerializedName("isLiked")
    @Expose
    var isLiked: Boolean? = null

    @SerializedName("likeCount")
    @Expose
    var likeCount: Int? = null

    @SerializedName("commentCount")
    @Expose
    val commentCount: Int? = null

    @SerializedName("userModel")
    @Expose
    val userModel: UserModel? = null
}

package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.post

import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.UserModel

class PostModel(
    val id: String,
    var description: String,
    var createdDate: String,
    var isLiked: Boolean,
    var likeCount: Int,
    var commentCount: Int,
    var userModel: UserModel?
)
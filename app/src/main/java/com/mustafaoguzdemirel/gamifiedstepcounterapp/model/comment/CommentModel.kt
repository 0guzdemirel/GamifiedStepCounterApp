package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.comment

import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.UserModel

class CommentModel(
    val id: String,
    var description: String,
    var createdDate: String,
    var userModel: UserModel?,
    var parentPostId: String
)
package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.social.post

import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.comment.CommentModel
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.post.PostModel

interface PostCallback {
    fun onPostCreated(postModel: PostModel) {}
    fun onCommentAdded(commentModel: CommentModel) {}
}
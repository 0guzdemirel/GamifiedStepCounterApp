package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.post

import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.post.PostModel

interface PostClickListener {
    fun onClickPost(postModel: PostModel?)
    fun onClickPostLike(postModel: PostModel?, position: Int)
    fun onClickShowStats(postModel: PostModel?)
}
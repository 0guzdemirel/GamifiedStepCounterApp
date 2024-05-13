package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.comment

import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.comment.CommentModel

interface CommentClickListener {
    fun onClickShowStats(commentModel: CommentModel?)
}
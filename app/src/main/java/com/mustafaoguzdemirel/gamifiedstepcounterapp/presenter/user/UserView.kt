package com.mustafaoguzdemirel.gamifiedstepcounterapp.presenter.user

import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.comment.CommentResponse
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.post.PostResponse
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.purchasableContent.PurchasableContentResponse
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.list.UserListResponse

interface UserView {
    fun onGetStreakRankListSuccessful(userListResponse: UserListResponse?) {}
    fun onGetAvgStepRankListSuccessful(userListResponse: UserListResponse?) {}
    fun onGetContentsSuccessful(purchasableContentResponse: PurchasableContentResponse?) {}
    fun onGetPostsSuccessful(postResponse: PostResponse?) {}
    fun onGetCommentsSuccessful(commentResponse: CommentResponse?) {}
    fun onAddPostSuccessful() {}
    fun onAddCommentSuccessful() {}
    fun onLikePostSuccessful() {}
    fun onBuyContentSuccessful() {}
    fun onError() {}
}

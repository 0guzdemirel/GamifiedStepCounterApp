package com.mustafaoguzdemirel.gamifiedstepcounterapp.presenter.user

import com.mustafaoguzdemirel.gamifiedstepcounterapp.api.ApiInterface
import com.mustafaoguzdemirel.gamifiedstepcounterapp.api.AppApiClient
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.base.BaseResponse
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.comment.CommentResponse
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.post.PostResponse
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.purchasableContent.PurchasableContentResponse
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.list.UserListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserPresenter() {
    private val apiInterface = AppApiClient.getClientWithToken().create(
        ApiInterface::class.java
    )

    fun getStreakRanking(
        userView: UserView
    ) {
        apiInterface.getStreakRanking(
        )?.enqueue(object : Callback<UserListResponse?> {
            override fun onResponse(
                call: Call<UserListResponse?>,
                response: Response<UserListResponse?>
            ) {
                try {
                    if (response.isSuccessful && response.body()?.meta?.code == 200) {
                        userView.onGetStreakRankListSuccessful(response.body())
                    } else {
                        userView.onError()
                    }
                } catch (ex: Exception) {
                    userView.onError()
                }
            }

            override fun onFailure(call: Call<UserListResponse?>, t: Throwable) {
                userView.onError()
            }
        })
    }

    fun getAvgStepRanking(
        userView: UserView
    ) {
        apiInterface.getAvgStepRanking(
        )?.enqueue(object : Callback<UserListResponse?> {
            override fun onResponse(
                call: Call<UserListResponse?>,
                response: Response<UserListResponse?>
            ) {
                try {
                    if (response.isSuccessful && response.body()?.meta?.code == 200) {
                        userView.onGetAvgStepRankListSuccessful(response.body())
                    } else {
                        userView.onError()
                    }
                } catch (ex: Exception) {
                    userView.onError()
                }
            }

            override fun onFailure(call: Call<UserListResponse?>, t: Throwable) {
                userView.onError()
            }
        })
    }

    fun getContents(
        userView: UserView
    ) {
        apiInterface.getContents(
        )?.enqueue(object : Callback<PurchasableContentResponse?> {
            override fun onResponse(
                call: Call<PurchasableContentResponse?>,
                response: Response<PurchasableContentResponse?>
            ) {
                try {
                    if (response.isSuccessful && response.body()?.meta?.code == 200) {
                        userView.onGetContentsSuccessful(response.body())
                    } else {
                        userView.onError()
                    }
                } catch (ex: Exception) {
                    userView.onError()
                }
            }

            override fun onFailure(call: Call<PurchasableContentResponse?>, t: Throwable) {
                userView.onError()
            }
        })
    }

    fun getPosts(
        userView: UserView
    ) {
        apiInterface.getPosts(
        )?.enqueue(object : Callback<PostResponse?> {
            override fun onResponse(
                call: Call<PostResponse?>,
                response: Response<PostResponse?>
            ) {
                try {
                    if (response.isSuccessful && response.body()?.meta?.code == 200) {
                        userView.onGetPostsSuccessful(response.body())
                    } else {
                        userView.onError()
                    }
                } catch (ex: Exception) {
                    userView.onError()
                }
            }

            override fun onFailure(call: Call<PostResponse?>, t: Throwable) {
                userView.onError()
            }
        })
    }

    fun getComments(
        postId: String?,
        userView: UserView
    ) {
        apiInterface.getComments(
        )?.enqueue(object : Callback<CommentResponse?> {
            override fun onResponse(
                call: Call<CommentResponse?>,
                response: Response<CommentResponse?>
            ) {
                try {
                    if (response.isSuccessful && response.body()?.meta?.code == 200) {
                        userView.onGetCommentsSuccessful(response.body())
                    } else {
                        userView.onError()
                    }
                } catch (ex: Exception) {
                    userView.onError()
                }
            }

            override fun onFailure(call: Call<CommentResponse?>, t: Throwable) {
                userView.onError()
            }
        })
    }

    fun addPost(
        postContent: String?,
        userView: UserView
    ) {
        apiInterface.addPost(
            postContent
        )?.enqueue(object : Callback<BaseResponse?> {
            override fun onResponse(
                call: Call<BaseResponse?>,
                response: Response<BaseResponse?>
            ) {
                try {
                    if (response.isSuccessful && response.body()?.meta?.code == 200) {
                        userView.onAddPostSuccessful()
                    } else {
                        userView.onError()
                    }
                } catch (ex: Exception) {
                    userView.onError()
                }
            }

            override fun onFailure(call: Call<BaseResponse?>, t: Throwable) {
                userView.onError()
            }
        })
    }

    fun likePost(
        postId: String?,
        isLiked: Boolean?,
        userView: UserView
    ) {
        apiInterface.likePost(
            postId,
            isLiked
        )?.enqueue(object : Callback<BaseResponse?> {
            override fun onResponse(
                call: Call<BaseResponse?>,
                response: Response<BaseResponse?>
            ) {
                try {
                    if (response.isSuccessful && response.body()?.meta?.code == 200) {
                        userView.onLikePostSuccessful()
                    } else {
                        userView.onError()
                    }
                } catch (ex: Exception) {
                    userView.onError()
                }
            }

            override fun onFailure(call: Call<BaseResponse?>, t: Throwable) {
                userView.onError()
            }
        })
    }

    fun addComment(
        postId: String?,
        commentContent: String?,
        userView: UserView
    ) {
        apiInterface.addComment(
            postId,
            commentContent
        )?.enqueue(object : Callback<BaseResponse?> {
            override fun onResponse(
                call: Call<BaseResponse?>,
                response: Response<BaseResponse?>
            ) {
                try {
                    if (response.isSuccessful && response.body()?.meta?.code == 200) {
                        userView.onAddCommentSuccessful()
                    } else {
                        userView.onError()
                    }
                } catch (ex: Exception) {
                    userView.onError()
                }
            }

            override fun onFailure(call: Call<BaseResponse?>, t: Throwable) {
                userView.onError()
            }
        })
    }

    fun buyContent(
        contentId: String?,
        userView: UserView
    ) {
        apiInterface.buyContent(
            contentId,
        )?.enqueue(object : Callback<BaseResponse?> {
            override fun onResponse(
                call: Call<BaseResponse?>,
                response: Response<BaseResponse?>
            ) {
                try {
                    if (response.isSuccessful && response.body()?.meta?.code == 200) {
                        userView.onBuyContentSuccessful()
                    } else {
                        userView.onError()
                    }
                } catch (ex: Exception) {
                    userView.onError()
                }
            }

            override fun onFailure(call: Call<BaseResponse?>, t: Throwable) {
                userView.onError()
            }
        })
    }
}
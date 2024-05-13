package com.mustafaoguzdemirel.gamifiedstepcounterapp.api

import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.base.BaseResponse
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.comment.CommentResponse
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.post.PostResponse
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.purchasableContent.PurchasableContentResponse
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.UserResponse
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.list.UserListResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @FormUrlEncoded
    @POST("User/Login")
    fun login(
        @Field("email") email: String?,
        @Field("password") password: String?,
    ): Call<UserResponse?>?

    @FormUrlEncoded
    @POST("User/Register")
    fun register(
        @Field("email") email: String?,
        @Field("password") password: String?,
        @Field("name") name: String?,
        @Field("avataarId") avataarId: String?
    ): Call<UserResponse?>?

    @FormUrlEncoded
    @POST("User/UpdateTodaysStep")
    fun updateTodaysStep(
        @Field("todaysStep") todaysStep: Int?,
    ): Call<BaseResponse?>?

    @GET("Content/GetPosts")
    fun getPosts(): Call<PostResponse?>?

    @GET("Content/GetComments")
    fun getComments(): Call<CommentResponse?>?

    @FormUrlEncoded
    @POST("Content/AddPost")
    fun addPost(
        @Field("postContent") postContent: String?,
    ): Call<BaseResponse?>?

    @FormUrlEncoded
    @POST("Content/LikePost")
    fun likePost(
        @Field("postId") postId: String?,
        @Field("isLiked") isLiked: Boolean?,
    ): Call<BaseResponse?>?

    @FormUrlEncoded
    @POST("Content/AddComment")
    fun addComment(
        @Field("postId") postId: String?,
        @Field("commentContent") commentContent: String?,
    ): Call<BaseResponse?>?

    @GET("Content/GetStreakRanking")
    fun getStreakRanking(): Call<UserListResponse?>?

    @GET("Content/GetAvgStepRanking")
    fun getAvgStepRanking(): Call<UserListResponse?>?

    @GET("Content/GetContents")
    fun getContents(): Call<PurchasableContentResponse?>?

    @FormUrlEncoded
    @POST("Content/BuyContent")
    fun buyContent(
        @Field("contentId") contentId: String?
    ): Call<BaseResponse?>?
}

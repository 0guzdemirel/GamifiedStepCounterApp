package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserModel {
    @SerializedName("token")
    @Expose
    val token: String? = null

    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("email")
    @Expose
    val email: String? = null

    @SerializedName("avatarId")
    @Expose
    val avatarId: String? = null

    @SerializedName("streakCount")
    @Expose
    val streakCount: Int? = null

    @SerializedName("avgStepCount")
    @Expose
    val avgStepCount: Int? = null

    @SerializedName("streakRanking")
    @Expose
    val streakRanking: Int? = null

    @SerializedName("avgStepRanking")
    @Expose
    val avgStepRanking: Int? = null

    @SerializedName("todaysStepCount")
    @Expose
    val todaysStepCount: Int? = null

    @SerializedName("coin")
    @Expose
    val coin: Int? = null
}

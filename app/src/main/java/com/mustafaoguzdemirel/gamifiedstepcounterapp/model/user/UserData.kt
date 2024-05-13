package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserData {
    @SerializedName("innerData")
    @Expose
    val userModel: UserModel? = null

    @SerializedName("message")
    @Expose
    val message: String? = null
}

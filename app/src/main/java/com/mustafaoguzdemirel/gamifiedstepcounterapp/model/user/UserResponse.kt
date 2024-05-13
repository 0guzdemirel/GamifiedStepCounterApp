package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.base.Meta

class UserResponse {
    @SerializedName("meta")
    @Expose
    val meta: Meta? = null

    @SerializedName("data")
    @Expose
    val userData: UserData? = null
}

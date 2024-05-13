package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.list

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.base.Meta

class UserListResponse {
    @SerializedName("meta")
    @Expose
    val meta: Meta? = null

    @SerializedName("data")
    @Expose
    val userListData: UserListData? = null
}

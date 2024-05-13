package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.list

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.UserModel

class UserListData {
    @SerializedName("innerData")
    @Expose
    val userList: MutableList<UserModel?>? = null


    @SerializedName("message")
    @Expose
    val message: String? = null
}

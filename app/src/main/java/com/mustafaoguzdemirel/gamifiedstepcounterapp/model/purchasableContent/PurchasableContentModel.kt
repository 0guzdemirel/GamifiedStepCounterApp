package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.purchasableContent

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.UserModel

class PurchasableContentModel {
    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("url")
    @Expose
    val url: String? = null

    @SerializedName("description")
    @Expose
    val description: String? = null

    @SerializedName("typeId")
    @Expose
    val typeId: String? = null

    @SerializedName("price")
    @Expose
    val price: Int? = null

    @SerializedName("userModel")
    @Expose
    val userModel: UserModel? = null

    @SerializedName("isOwned")
    @Expose
    var isOwned: Boolean? = null
}
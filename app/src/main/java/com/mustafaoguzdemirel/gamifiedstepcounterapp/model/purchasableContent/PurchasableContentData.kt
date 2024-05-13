package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.purchasableContent

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PurchasableContentData {
    @SerializedName("innerData")
    @Expose
    val purchasableList: MutableList<PurchasableContentModel?>? = null

    @SerializedName("message")
    @Expose
    val message: String? = null
}

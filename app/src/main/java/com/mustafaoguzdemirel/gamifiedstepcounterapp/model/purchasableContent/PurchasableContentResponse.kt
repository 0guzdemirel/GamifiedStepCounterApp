package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.purchasableContent

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.base.Meta

class PurchasableContentResponse {
    @SerializedName("meta")
    @Expose
    val meta: Meta? = null

    @SerializedName("data")
    @Expose
    val purchasableContentData: PurchasableContentData? = null
}

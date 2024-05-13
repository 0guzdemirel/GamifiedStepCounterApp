package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.purchasableContent

import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.purchasableContent.PurchasableContentModel

interface PurchasableContentClickListener {
    fun onClickContent(purchasableContentModel: PurchasableContentModel?)
}
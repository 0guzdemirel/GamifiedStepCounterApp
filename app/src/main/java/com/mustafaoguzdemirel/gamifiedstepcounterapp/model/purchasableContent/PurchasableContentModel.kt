package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.purchasableContent

class PurchasableContentModel(
    var id: String,
    var typeId: String,
    var price: Int,
    var title: String,
    var description: String,
    var duration: Int,
    var url: String,
    var isOwned: Boolean,
) {
}
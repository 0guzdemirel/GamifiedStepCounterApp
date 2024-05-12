package com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user

class UserModel(
    val id: String,
    var email: String,
    var password: String,
    val name: String,
    var avatarId: Int,
    var streakCount: Int,
    var avgStepCount: Int,
    var streakRanking: Int,
    var avgStepRanking: Int,
    var todaysStepCount: Int,
    var coin: Int,
)
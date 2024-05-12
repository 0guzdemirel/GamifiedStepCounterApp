package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.start.register

import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.avatar.AvatarModel

interface AvatarCallback {
    fun onAvatarSelected(avatarModel: AvatarModel)
}